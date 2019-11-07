package com.example.faza1_baicuandrei;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Country implements Parcelable{
    private String name;
    private long population;
    private String capital;



    public Country(String name, long population, String capital) {
        this.name = name;
        this.population = population;
        this.capital = capital;

    }

    protected Country(Parcel in) {
        name = in.readString();
        population = in.readLong();
        capital = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(population);
        dest.writeString(capital);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public static Comparator<Country> alphabetical = new Comparator<Country>() {

        public int compare(Country c1, Country c2) {
            return c1.getName().compareTo(c2.getName());
        }
    };



}
