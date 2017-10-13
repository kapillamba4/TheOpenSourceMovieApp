package com.example.kapillamba4.theopensourcemovieapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kapil on 12/10/17.
 */

public class TvShow {
    private int id;
    @SerializedName("imdb_id")
    private String name;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private String overview;
    private String status;
    @SerializedName("vote_average")
    private int voteAverage;
    ArrayList<Genre> genres;
}