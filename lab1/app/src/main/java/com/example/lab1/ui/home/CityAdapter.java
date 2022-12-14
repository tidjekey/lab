package com.example.lab1.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.R;
import com.example.lab1.dbroom.dbCityData;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder>{

    Context context;

    List<dbCityData> cityArrayList;

    public CityAdapter(Context context, List<dbCityData> cityArrayList) {
        this.context = context;
        this.cityArrayList = cityArrayList;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        dbCityData city = cityArrayList.get(position);
        holder.tvCity.setText(city.name);
    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView tvCity;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.txt_city);
        }
    }
}

