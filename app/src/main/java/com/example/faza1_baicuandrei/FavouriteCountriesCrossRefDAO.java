package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FavouriteCountriesCrossRefDAO {
    @Insert
    void insertFavCountries(FavouriteCountriesCrossRef favouriteCountriesCrossRef);


    @Delete
    void removeFromFav(FavouriteCountriesCrossRef favouriteCountriesCrossRef);

    @Query("SELECT * FROM favourites WHERE countryID == :countryID AND userID == :userID")
    FavouriteCountriesCrossRef checIfFavExists(String countryID, int userID);

    @Query("SELECT c.CountryID, c.name,  c.population, c.capital  FROM country c, favourites f " +
            "WHERE c.CountryID == f.CountryID AND f.UserID == :userID")
    List<Country> returnUsersFavouriteCountries(int userID);
}
