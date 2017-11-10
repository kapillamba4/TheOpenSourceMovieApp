package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.DetailMovie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kapil on 13/10/17.
 */

public interface MovieService {
    @GET("movie/popular")
    Call<WrapperMovie> getPopularMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/top_rated")
    Call<WrapperMovie> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/upcoming")
    Call<WrapperMovie> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/{movie_id}")
    Call<DetailMovie> getDetailMovie(@Path("movie_id") String id, @Query("api_key") String apiKey);
}
