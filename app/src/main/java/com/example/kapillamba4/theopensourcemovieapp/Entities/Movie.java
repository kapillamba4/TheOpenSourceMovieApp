package com.example.kapillamba4.theopensourcemovieapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kapil on 12/10/17.
 */

public class Movie {
    // TODO
    private int id;
    @SerializedName("imdb_id")
    private String imdbId;
    private String name;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("first_air_date")
    private String releaseDate;
    private String overview;
    private String status;
    @SerializedName("vote_average")
    private int voteAverage;
    private int runtime;
    ArrayList<Genre> genres;
}
