package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface FavouriteCountriesCrossRefDAO {
    @Insert
    void insertFavCountries(FavouriteCountriesCrossRef favouriteCountriesCrossRef);
}
