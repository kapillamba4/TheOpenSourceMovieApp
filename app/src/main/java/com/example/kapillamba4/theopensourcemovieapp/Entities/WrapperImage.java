package com.example.kapillamba4.theopensourcemovieapp.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kapil on 4/11/17.
 */

public class WrapperImage {
    @SerializedName("backdrops")
    @Expose
    ArrayList<ResourceImage> backdrops;
    @SerializedName("posters")
    @Expose
    ArrayList<ResourceImage> posters;
}
