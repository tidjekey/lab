package com.example.lab1.dbroom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IdbCityData {
    @Query("SELECT * FROM dbcitydata")
    List<dbCityData> getAll();

    @Query("SELECT * FROM dbcitydata WHERE id = :id")
    dbCityData getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(dbCityData cityData);
}
