package com.example.lab1.dbroom;

import android.service.autofill.UserData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface IdbUserData {
    @Query("SELECT * FROM dbUserData")
    List<dbUserData> getAll();

    @Query("SELECT * FROM dbuserdata WHERE login = :login")
    dbUserData getByLogin(String login);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(dbUserData userData);
}
