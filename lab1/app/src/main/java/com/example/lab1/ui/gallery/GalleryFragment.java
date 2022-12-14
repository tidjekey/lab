package com.example.lab1.ui.gallery;

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

import com.example.lab1.MainActivity2;
import com.example.lab1.R;
import com.example.lab1.databinding.FragmentGalleryBinding;
import com.example.lab1.dbroom.dbCityData;
import com.example.lab1.ui.home.CityAdapter;
import com.example.lab1.ui.home.CityData;
import com.example.lab1.ui.home.RecyclerItemClickListener;
import com.github.kimkevin.cachepot.CachePot;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    private dbCityData currentCity = new dbCityData();

    TextView tvName;
    TextView tvCountry;
    TextView tvPopulation;
    TextView tvLanguage;
    TextView tvSquare;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        currentCity = MainActivity2.currentCity;

        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tv_gal_name);
        tvCountry = view.findViewById(R.id.tv_gal_country);
        tvPopulation = view.findViewById(R.id.tv_gal_population);
        tvLanguage = view.findViewById(R.id.tv_gal_language);
        tvSquare = view.findViewById(R.id.tv_gal_square);

        tvName.setText(currentCity.name);
        tvCountry.setText(currentCity.country);
        tvPopulation.setText(String.valueOf(currentCity.population));
        tvLanguage.setText(currentCity.language);
        tvSquare.setText(String.valueOf(currentCity.square));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}