package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularMovie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularTv;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kapil on 12/10/17.
 */

public interface TvService {
    @GET("tv/popular")
    Call<PopularTv> getPopularTVShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/top_rated")
    Call<PopularTv> getTopRatedTvShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/upcoming")
    Call<PopularTv> getUpcomingTvShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/{id}")
    Call<PopularMovie> getDetailTvShow(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("tv/{id}/keywords")
    Call<PopularMovie> getKeywordsTvShow(@Path("id") String id, @Query("api_key") String apiKey);

}