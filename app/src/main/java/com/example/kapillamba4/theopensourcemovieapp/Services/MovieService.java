package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularMovie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kapil on 13/10/17.
 */

public interface MovieService {
    @GET("movie/popular")
    Call<PopularMovie> getPopularMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/top_rated")
    Call<PopularMovie> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/upcoming")
    Call<PopularMovie> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/{id}")
    Call<PopularMovie> getDetailMovie(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("movie/{id}/keywords")
    Call<PopularMovie> getKeywordsMovie(@Path("id") String id, @Query("api_key") String apiKey);
}
