package com.example.lab1.dbroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class dbCityData {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("Population")
    @Expose
    public int population;

    @SerializedName("Country")
    @Expose
    public String country;

    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("language")
    @Expose
    public String language;

    @SerializedName("square")
    @Expose
    public int square;
}
