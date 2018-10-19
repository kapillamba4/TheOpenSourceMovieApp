package com.kapillamba4.thecompletemovieguide.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.kapillamba4.thecompletemovieguide.Utils.CONTRACT;

/**
 * Created by kapil on 3/1/18.
 */

@Entity(tableName = CONTRACT.FAVOURITES_TABLE_NAME)
public class FavouriteEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = CONTRACT.FAVOURITES_ID)
    private String id;
    @ColumnInfo(name = CONTRACT.FAVOURITES_NAME)
    private String name;
    @ColumnInfo(name = CONTRACT.FAVOURITES_RELEASE_DATE)
    private String releaseDate;
    @ColumnInfo(name = CONTRACT.FAVOURITES_STATUS)
    private String status;
    @ColumnInfo(name = CONTRACT.FAVOURITES_POSTER_PATH)
    private String posterPath;
    @ColumnInfo(name = CONTRACT.FAVOURITES_OVERVIEW)
    private String overview;
    @ColumnInfo(name = CONTRACT.FAVOURITES_GENRES)
    private String genres;
    @ColumnInfo(name = CONTRACT.FAVOURITES_TYPE)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
