package com.example.lab1.dbroom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IdbSavedCity {
    @Query("SELECT * FROM dbsavedcity")
    List<dbSavedCity> getAll();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(dbSavedCity savedCity);

}
