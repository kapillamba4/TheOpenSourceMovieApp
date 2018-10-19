package com.kapillamba4.thecompletemovieguide.Services;

import com.kapillamba4.thecompletemovieguide.Entities.DetailTv;
import com.kapillamba4.thecompletemovieguide.Entities.WrapperTvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kapil on 12/10/17.
 */

public interface TvService {
    @GET("tv/popular")
    Call<WrapperTvShow> getPopularTVShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/top_rated")
    Call<WrapperTvShow> getTopRatedTvShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/airing_today")
    Call<WrapperTvShow> getAiringTvShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/{tv_id}")
    Call<DetailTv> getDetailTvShow(@Path("tv_id") String id, @Query("api_key") String apiKey);
}