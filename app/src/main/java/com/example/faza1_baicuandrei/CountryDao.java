package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert
    void insertCountry(Country country);

    @Query("SELECT name FROM country")
    List<String> selectAllCountries();
}
