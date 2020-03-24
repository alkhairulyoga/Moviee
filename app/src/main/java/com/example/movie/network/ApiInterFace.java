package com.example.movie.network;

import com.example.movie.Model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterFace {
    @GET(Constant.MOVIE_PATH + "/top_rated")
    Call<Movie> topRatedMovies(
            @Query("page") int page);

    @GET(Constant.MOVIE_PATH + "/popular")
    Call<Movie> popularMovies(
            @Query("page") int page);

    @GET(Constant.MOVIE_PATH + "{movie_id}/similiar")
    Call<Movie> similiarMovies(
            @Query("page") int page);
}
