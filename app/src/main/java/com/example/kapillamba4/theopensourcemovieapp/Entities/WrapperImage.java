package com.kapillamba4.thecompletemovieguide.Entities;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kapil on 4/11/17.
 */

public class WrapperImage {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrops")
    @Expose
    private List<ResourceImage> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<ResourceImage> posters = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ResourceImage> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<ResourceImage> backdrops) {
        this.backdrops = backdrops;
    }

    public List<ResourceImage> getPosters() {
        return posters;
    }

    public void setPosters(List<ResourceImage> posters) {
        this.posters = posters;
    }
}
