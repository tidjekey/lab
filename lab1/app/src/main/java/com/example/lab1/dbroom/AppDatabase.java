package com.example.lab1.dbroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {dbCityData.class, dbUserData.class, dbSavedCity.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract  IdbCityData IdbCityData();
    public abstract  IdbUserData IdbUserData();
    public abstract  IdbSavedCity IdbSavedCity();
}
