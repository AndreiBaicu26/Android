package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"countryID","userID" })
public class FavouriteCountriesCrossRef {
    @NonNull
    public String countryID;
    @NonNull
    public int userID;


    public FavouriteCountriesCrossRef(@NonNull String countryID, @NonNull int userID) {
        this.countryID = countryID;
        this.userID = userID;
    }

    public String getCountryID() {
        return countryID;
    }

    @NonNull
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public int getUserID() {
        return userID;
    }
    @NonNull
    public void setUserID(int userID) {
        this.userID = userID;
    }
}

