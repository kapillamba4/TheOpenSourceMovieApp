package com.example.kapillamba4.theopensourcemovieapp.Entities;

/**
 * Created by kapil on 12/10/17.
 */

public class TvShow {
    private String title;
    private String thumbnail;
    private String releaseDate;
    private String rating;
    private String description;
    private String[] images;
    private String[] trailerUrls;

    TvShow(String title, String thumbnail, String releaseDate, String rating, String description, String[] images, String[] trailerUrls) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.description = description;
        this.images = images;
        this.trailerUrls = trailerUrls;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String[] getImages() {
        return images;
    }

    public String[] getTrailerUrls() {
        return trailerUrls;
    }

    public String getDescription() {
        return description;
    }
}
