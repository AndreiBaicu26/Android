package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.CheckResult;

import com.example.faza1_baicuandrei.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser (User user);

    @Query("SELECT email FROM user")
    List<String> selectEmailFromUser();

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    User checkIfUserExists(String email, String password);

    @Query("SELECT * FROM user WHERE email = :email")
    User checkUserWithEmailGiven(String email);

    @Update
    void updatePassword(User user);
}
