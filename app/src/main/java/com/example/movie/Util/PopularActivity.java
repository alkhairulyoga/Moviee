package com.example.movie.Util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.movie.Adapter.MovieListAdapter;
import com.example.movie.Model.Movie;
import com.example.movie.R;
import com.example.movie.network.ApiService;

import java.net.Socket;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularActivity extends AppCompatActivity {
    MovieListAdapter movieListAdapter;
    RecyclerView recyclerView;
    private int page = 1;
    GridLayoutManager gridLayoutManager;
    ApiService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        movieListAdapter = new MovieListAdapter(this);
        recyclerView = findViewById(R.id.rv_movie);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieListAdapter);

        loadData();
    }

    private void loadData() {
        service = new ApiService();
        service.getPopularMovies(page, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Movie movie = (Movie) response.body();
                //Long.valueOf(""+movie.getResults());
                if(movie!=null) {
                    if (movieListAdapter != null) {
                        movieListAdapter.addAll(movie.getResults());
                    }
                }else {
                    Toast.makeText(getBaseContext(), "Tidak ada data", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toast.makeText(getBaseContext(),
                            "Request Timeout.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(),
                            "Connection Error!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
