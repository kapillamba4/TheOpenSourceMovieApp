package com.example.kapillamba4.theopensourcemovieapp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.kapillamba4.theopensourcemovieapp.Utils.CONTRACT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kapil on 3/1/18.
 */

@Dao
public interface FavouriteEntityDao {
    @Query("SELECT * FROM " + CONTRACT.FAVOURITES_TABLE_NAME)
    List<FavouriteEntity> getAll();

    @Query("SELECT * FROM " + CONTRACT.FAVOURITES_TABLE_NAME + " WHERE " + CONTRACT.FAVOURITES_TYPE + " = (:type)")
    List<FavouriteEntity> getSpecific(String type);

    @Insert
    void insert(FavouriteEntity minifiedEntity);

    @Delete
    void delete(FavouriteEntity minifiedEntity);
}
