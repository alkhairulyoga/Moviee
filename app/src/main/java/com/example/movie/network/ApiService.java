package com.example.movie.network;

import com.example.movie.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private ApiInterFace apiInterFace;

    public ApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(builder())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterFace = retrofit.create(ApiInterFace.class);
    }

    private OkHttpClient builder() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(20, TimeUnit.SECONDS);
        okHttpClient.readTimeout(80, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(interceptor());
        }
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url= request.url()
                        .newBuilder()
                        .addQueryParameter("api_key", Constant.API_KEY)
                        .addQueryParameter("language", Constant.LANG_EN)
                        .build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });
        return okHttpClient.build();
    }

    private static HttpLoggingInterceptor interceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
    public void getTopRatedMovies(int page, Callback callback) {
        apiInterFace.topRatedMovies(page).enqueue(callback);
    }
    public void getPopularMovies(int page, Callback callback) {
        apiInterFace.popularMovies(page).enqueue(callback);
    }

    public void getSimiliarMovies(int page, Callback callback) {
        apiInterFace.similiarMovies(page).enqueue(callback);
    }
}
