package com.example.lab1;

import android.app.Application;

import androidx.room.Room;

import com.example.lab1.dbroom.AppDatabase;


public class App extends Application {
    public static App instance;

    private AppDatabase database;
    public static String login;
    public static int id;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {return instance;}
    public AppDatabase getDatabase() {return database;}

}
