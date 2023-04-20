package com.example.my_weatherapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.example.my_weatherapp.Adapter.ForeCastAdapter;
import com.example.my_weatherapp.Adapter.ForeCastDayAdapter;
import com.example.my_weatherapp.Interface.OnFetchingDataListener;
import com.example.my_weatherapp.Model.APIResponse;
import com.example.my_weatherapp.Model.Current;
import com.example.my_weatherapp.Model.ForeCast.ForeCast;
import com.example.my_weatherapp.Model.ForeCast.ForeCastDay;
import com.example.my_weatherapp.Model.ForeCast.Hour;
import com.example.my_weatherapp.Model.Location;
import com.example.my_weatherapp.Model.RequestManager;
import com.example.my_weatherapp.R;
import com.example.my_weatherapp.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RequestManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manager = new RequestManager(this);
        manager.getWeather(listener,"London");
        searchCountry();
        binding.Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                manager.getWeather(listener,"London");
                binding.Refresh.setRefreshing(false);
            }
        });

    }

    public final OnFetchingDataListener listener  = new OnFetchingDataListener() {
        @Override
        public void OnFetchingData(Location location, Current current, ForeCast foreCast, String message) {
            showWeatherCurrent(location,current);
            for (int i = 0; i<foreCast.getForecastday().size();i++){
                showDataForeCast(foreCast.getForecastday().get(i).getHour());
            }
            showDataForeCastDay(foreCast.getForecastday());
        }

        @Override
        public void OnError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    public void showDataForeCastDay(List<ForeCastDay>foreCastDays){
        ForeCastDayAdapter foreCastDayAdapter = new ForeCastDayAdapter(foreCastDays,MainActivity.this);
        binding.recyclerViewDay.setHasFixedSize(true);
        binding.recyclerViewDay.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        binding.recyclerViewDay.setAdapter(foreCastDayAdapter);

    }

    public void showDataForeCast(List<Hour>hours){

        ForeCastAdapter foreCastAdapter = new ForeCastAdapter(MainActivity.this,hours);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerView.setAdapter(foreCastAdapter);

    }

    public void showWeatherCurrent(Location location, Current current){
        binding.country.setText(location.getName());
        binding.tempWeather.setText(current.getTemp_c());
        if (current.getIs_day().equals("0")){
            binding.background.setImageResource(R.drawable.night);
        }
        else {
            binding.background.setImageResource(R.drawable.morning);
        }
        Picasso.get().load("https:"+current.getCondition().getIcon()).into(binding.iconWeather);
        binding.conditionWeather.setText(current.getCondition().getText());
    }

    public void searchCountry(){
        binding.searchCountry.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                manager.getWeather(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}