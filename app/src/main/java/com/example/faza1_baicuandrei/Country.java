package com.example.faza1_baicuandrei;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Comparator;

@Entity(tableName = "country")
public class Country implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "CountryID")
    private String id;
    @NonNull
    private String name;
    @NonNull
    private long population;
    @NonNull
    private String capital;


    public Country(@NonNull String id,@NonNull String name, @NonNull long population,@NonNull String capital) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.capital = capital;

    }

    protected Country(Parcel in) {
        id = in.readString();
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
        dest.writeString(id);
        dest.writeString(name);
        dest.writeLong(population);
        dest.writeString(capital);

    }


    public String getId() {
        return id;
    }

    @NonNull
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @NonNull
    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }
    @NonNull
    public void setPopulation(long population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }
    @NonNull
    public void setCapital(String capital) {
        this.capital = capital;
    }

    public static Comparator<Country> alphabetical = new Comparator<Country>() {

        public int compare(Country c1, Country c2) {
            return c1.getName().compareTo(c2.getName());
        }
    };

    public static Comparator<Country> popDesc = new Comparator<Country>() {

        public int compare(Country c1, Country c2) {
            if (c1.getPopulation() > c2.getPopulation())
                return -1;
            else if (c1.getPopulation() < c2.getPopulation())
                return 1;
            else
                return 0;
        }
    };

    public static Comparator<Country> popAsc = new Comparator<Country>() {

        public int compare(Country c1, Country c2) {
            if (c1.getPopulation() < c2.getPopulation())
                return -1;
            else if (c1.getPopulation() > c2.getPopulation())
                return 1;
            else
                return 0;
        }
    };


}
