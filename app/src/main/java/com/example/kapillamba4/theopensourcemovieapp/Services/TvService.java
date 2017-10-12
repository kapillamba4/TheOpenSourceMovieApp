package com.example.kapillamba4.theopensourcemovieapp.Services;

import com.example.kapillamba4.theopensourcemovieapp.Entities.TvShow;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by kapil on 12/10/17.
 */

public interface TvService {
    @GET("/tv/popular?api_key=")
    Call<ArrayList<TvShow> > getTvShows();
}
