package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kapil on 13/10/17.
 */

public interface MovieService {

    @GET("movie/popular")
    Call<PopularMovie> getPopularMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);
}
