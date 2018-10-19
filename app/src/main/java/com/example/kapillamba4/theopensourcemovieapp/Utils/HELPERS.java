package com.kapillamba4.thecompletemovieguide.Utils;

import com.kapillamba4.thecompletemovieguide.Database.FavouriteEntity;
import com.kapillamba4.thecompletemovieguide.Entities.DetailMovie;
import com.kapillamba4.thecompletemovieguide.Entities.DetailTv;
import com.kapillamba4.thecompletemovieguide.Entities.Genre;

import java.util.ArrayList;

/**
 * Created by kapil on 3/1/18.
 */

public class HELPERS {
    // decompose movie objects to room compatible pojos
    public static FavouriteEntity convert(DetailMovie movie) {
        FavouriteEntity minifiedEntity = new FavouriteEntity();
        minifiedEntity.setName(movie.getTitle());
        minifiedEntity.setId(movie.getId().toString());
        minifiedEntity.setOverview(movie.getOverview());
        minifiedEntity.setPosterPath(movie.getPosterPath());
        minifiedEntity.setReleaseDate(movie.getReleaseDate());
        minifiedEntity.setStatus(movie.getStatus());
        StringBuilder builder = new StringBuilder("");
        for(Genre genre: movie.getGenres()) {
            builder.append(genre.getName()+",");
        }

        String genres = builder.toString().substring(0, builder.length()-1);
        minifiedEntity.setGenres(genres);
        return minifiedEntity;
    }

    public static FavouriteEntity convert(DetailTv tv) {
        FavouriteEntity minifiedEntity = new FavouriteEntity();
        minifiedEntity.setName(tv.getName());
        minifiedEntity.setId(tv.getId().toString());
        minifiedEntity.setOverview(tv.getOverview());
        minifiedEntity.setPosterPath(tv.getPosterPath());
        minifiedEntity.setReleaseDate(tv.getFirstAirDate());
        minifiedEntity.setStatus(tv.getStatus());

        StringBuilder builder = new StringBuilder("");
        for(Genre genre: tv.getGenres()) {
            builder.append(genre.getName()+",");
        }

        String genres = builder.toString().substring(0, builder.length()-1);
        minifiedEntity.setGenres(genres);
        return minifiedEntity;
    }

//    public static MinifiedEntity convertMovie(Movie movie) {
//        MinifiedEntity minifiedEntity = new MinifiedEntity();
//        minifiedEntity.setName(movie.getTitle());
//        minifiedEntity.setId(movie.getId().toString());
//        minifiedEntity.setOverview(movie.getOverview());
//        minifiedEntity.setPosterPath(movie.getPosterPath());
//        minifiedEntity.setReleaseDate(movie.getReleaseDate());
//        minifiedEntity.setStatus("");
//        minifiedEntity.setGenres(movie.getGenres().toString());
//        return minifiedEntity;
}


