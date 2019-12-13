package com.example.faza1_baicuandrei;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "user")//a
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserID")
    private int id;
    @NonNull
    private String email;
    @NonNull
    private String password;

    public User(int id, @NonNull String email,@NonNull String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    protected User(Parcel in) {
        id = in.readInt();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(password);
    }
}
