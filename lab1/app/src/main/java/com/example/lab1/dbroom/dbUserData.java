package com.example.lab1.dbroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class dbUserData {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String login;
    public String password;
}
