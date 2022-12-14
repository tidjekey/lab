package com.example.lab1.ui.home;

import com.example.lab1.dbroom.dbCityData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RetrofitAPI {
    @GET("City2022.json")

    Call<List<dbCityData>> getCity();
}
