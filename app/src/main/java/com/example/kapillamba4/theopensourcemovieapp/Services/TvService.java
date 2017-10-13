package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularTV;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kapil on 12/10/17.
 */

public interface TvService {
    @GET("/tv/popular?api_key={api_key}")
    Call<ArrayList<PopularTV> > getTvShows(@Path("api_key") String key);
}
