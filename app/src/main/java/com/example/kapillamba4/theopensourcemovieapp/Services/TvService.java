package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularTv;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kapil on 12/10/17.
 */

public interface TvService {

    @GET("tv/popular")
    Call<PopularTv> getPopularTVShows(@Query("api_key") String apiKey, @Query("page") Integer page);
}