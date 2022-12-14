package com.example.lab1.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityData {

    public CityData(String name) {
        this.name = name;
    }

    public CityData(String name, String country, int population, String language, int square) {
        this.name = name;
        this.country = country;
        this.population = population;
        this.language = language;
        this.square = square;
    }

    @SerializedName("Population")
    @Expose
    public int population;

    @SerializedName("Country")
    @Expose
    public String country;

    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("Language")
    @Expose
    public String language;

    @SerializedName("square")
    @Expose
    public int square;
}
