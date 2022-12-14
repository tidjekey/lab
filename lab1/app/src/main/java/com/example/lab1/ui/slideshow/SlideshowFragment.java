package com.example.lab1.ui.slideshow;

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

import com.example.lab1.App;
import com.example.lab1.R;
import com.example.lab1.databinding.FragmentSlideshowBinding;
import com.example.lab1.dbroom.AppDatabase;
import com.example.lab1.dbroom.IdbCityData;
import com.example.lab1.dbroom.IdbSavedCity;
import com.example.lab1.dbroom.dbCityData;
import com.example.lab1.dbroom.dbSavedCity;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    AppDatabase db = App.getInstance().getDatabase();
    IdbCityData cityDao = db.IdbCityData();
    IdbSavedCity savedDao = db.IdbSavedCity();

    List<dbCityData> userCities = new ArrayList<>();

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<dbSavedCity> allSavedCities =  new ArrayList<>();
        allSavedCities = savedDao.getAll();

        for (int i = 0; i < allSavedCities.size(); i++) {
            if (allSavedCities.get(i).idUser == App.id) {
                dbCityData city = cityDao.getById(allSavedCities.get(i).idCity);
                userCities.add(city);
            }
        }

        for (int i = 0; i < userCities.size(); i++) {
            Log.i("saved city", String.valueOf(userCities.get(i).id) +
                    " " + userCities.get(i).name +
                    " " + userCities.get(i).country +
                    " " + userCities.get(i).language +
                    " " + userCities.get(i).population +
                    " " + userCities.get(i).square
            );
        }

    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}