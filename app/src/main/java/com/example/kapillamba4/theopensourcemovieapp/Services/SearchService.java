package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.SearchItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kapil on 28/10/17.
 */

public interface SearchService {
    @GET("search/{query}")
    Call<SearchItem> getSearchResult(@Path("query") String query);
}
