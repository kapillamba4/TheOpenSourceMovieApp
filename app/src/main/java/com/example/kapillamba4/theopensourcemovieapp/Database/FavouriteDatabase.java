package com.example.kapillamba4.theopensourcemovieapp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by kapil on 3/1/18.
 */

@Database(entities = {FavouriteEntity.class}, version = 1)
public abstract class FavouriteDatabase extends RoomDatabase {
    private static FavouriteDatabase INSTANCE;
    public abstract FavouriteEntityDao favouriteEntityDao();
}
