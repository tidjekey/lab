package com.example.lab1.dbroom;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class dbSavedCity {
    @PrimaryKey
    @NonNull
    public String id;
    public long idUser;
    public long idCity;
}
