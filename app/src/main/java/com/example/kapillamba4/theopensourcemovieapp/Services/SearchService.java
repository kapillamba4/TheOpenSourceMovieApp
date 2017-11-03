package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.MultiSearch;
import com.example.kapillamba4.theopensourcemovieapp.Entities.SearchItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kapil on 28/10/17.
 */

public interface SearchService {
    @GET("search/multi")
    Call<MultiSearch> getSearchResult(@Query("api_key") String apiKey, @Query("page") String page);
}
