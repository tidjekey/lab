package com.example.lab1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.App;
import com.example.lab1.MainActivity2;
import com.example.lab1.R;
import com.example.lab1.databinding.FragmentHomeBinding;
import com.example.lab1.dbroom.AppDatabase;
import com.example.lab1.dbroom.IdbCityData;
import com.example.lab1.dbroom.IdbSavedCity;
import com.example.lab1.dbroom.dbCityData;
import com.example.lab1.dbroom.dbSavedCity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private RecyclerView recyclerView;
    CityAdapter cityAdapter;

    AppDatabase db = App.getInstance().getDatabase();
    IdbCityData cityDao = db.IdbCityData();
    IdbSavedCity savedDao = db.IdbSavedCity();

    public static List<dbCityData> recyclerCityData = new ArrayList<dbCityData>();

    boolean isFinished = false;

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            Log.i("Thread HomeFragment", "InBackground");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
            Call<List<dbCityData>> call = retrofitAPI.getCity();

            call.enqueue(new Callback<List<dbCityData>>() {
                @Override
                public void onResponse(Call<List<dbCityData>> call, Response<List<dbCityData>> response) {
                    if (response.isSuccessful()) {
                        recyclerCityData.clear();

                        for (int i = 0; i < response.body().toArray().length ; i++) {
                            dbCityData city = new dbCityData();

                            city.id = i;
                            city.name = response.body().get(i).name;
                            city.country = response.body().get(i).country;
                            city.language = response.body().get(i).language;
                            city.population = response.body().get(i).population;
                            city.square = response.body().get(i).square;

                            cityDao.insert(city);

                            Log.i("city", String.valueOf(city.id) + " "
                                    + city.name + " "
                                    + city.country + " "
                                    + city.language + " "
                                    + city.population + " "
                                    + city.square
                            );

                        }

                        List<dbCityData> dbCities = cityDao.getAll();

                        recyclerCityData.addAll(dbCities);

                    }
                }

                @Override
                public void onFailure(Call<List<dbCityData>> call, Throwable t) {
                    Log.i("my data", "Failed to get data");
                }
            });
        }
    });


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        run();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cityAdapter = new CityAdapter(getContext(), recyclerCityData);
        recyclerView.setAdapter(cityAdapter);
        cityAdapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        MainActivity2.currentCity = recyclerCityData.get(position);
                        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_gallery);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        dbSavedCity savedCity = new dbSavedCity();
                        dbCityData city = recyclerCityData.get(position);

                        savedCity.id = String.valueOf(city.id) + "_" + String.valueOf(App.id);
                        savedCity.idCity = city.id;
                        savedCity.idUser = App.id;

                        savedDao.insert(savedCity);

                    }
                })
        );
    }

    private void run() {
        Log.i("Thread HomeFragment", "InBackground");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<List<dbCityData>> call = retrofitAPI.getCity();

        call.enqueue(new Callback<List<dbCityData>>() {
            @Override
            public void onResponse(Call<List<dbCityData>> call, Response<List<dbCityData>> response) {
                if (response.isSuccessful()) {
                    recyclerCityData.clear();

                    for (int i = 0; i < response.body().toArray().length ; i++) {
                        dbCityData city = new dbCityData();
                        city.id = i;
                        city.name = response.body().get(i).name;
                        city.country = response.body().get(i).country;
                        city.language = response.body().get(i).language;
                        city.population = response.body().get(i).population;
                        city.square = response.body().get(i).square;
                        cityDao.insert(city);

                        Log.i("city", String.valueOf(city.id) + " "
                                + city.name + " "
                                + city.country + " "
                                + city.language + " "
                                + city.population + " "
                                + city.square
                        );
                    }
                    List<dbCityData> dbCities = cityDao.getAll();
                    recyclerCityData.addAll(dbCities);
                }
            }

            @Override
            public void onFailure(Call<List<dbCityData>> call, Throwable t) {
                Log.i("my data", "Failed to get data");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
