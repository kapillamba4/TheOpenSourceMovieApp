package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.MultiSearch;
import com.example.kapillamba4.theopensourcemovieapp.Entities.ResourceImage;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperImage;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kapil on 4/11/17.
 */

public interface CommonService {
    @GET("{type}/{id}/videos")
    Call<WrapperVideo> getVideos(@Path("type") String type, @Path("id") String id, @Query("api_key") String apiKey);

    @GET("{type}/{id}/images")
    Call<WrapperImage> getImages(@Path("type") String type, @Path("id") String id, @Query("api_key") String apiKey);

    @GET("search/multi")
    Call<MultiSearch> getSearchResult(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") String page);
}
