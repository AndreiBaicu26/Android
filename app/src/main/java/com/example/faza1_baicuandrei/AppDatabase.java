package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.faza1_baicuandrei.Country;

@Database(entities = {User.class, Country.class, FavouriteCountriesCrossRef.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract CountryDao countryDao();
    public abstract FavouriteCountriesCrossRefDAO favouriteCountriesCrossRefDAO();
}