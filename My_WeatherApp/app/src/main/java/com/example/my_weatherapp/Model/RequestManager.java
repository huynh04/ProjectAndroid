package com.example.my_weatherapp.Model;

import android.content.Context;
import android.widget.Toast;

import com.example.my_weatherapp.Interface.OnFetchingDataListener;
import com.example.my_weatherapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context ctx;
    Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context ctx) {
        this.ctx = ctx;
    }

    public void getWeather(OnFetchingDataListener onFetchingDataListener,String query){
        CallWeatherAPI callWeatherAPI = retrofit.create(CallWeatherAPI.class);
        Call<APIResponse>call = callWeatherAPI.callWeather(ctx.getString(R.string.apiKey),query,"7");

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (call.isCanceled()){
                    Toast.makeText(ctx, "Not Found", Toast.LENGTH_SHORT).show();
                }
                onFetchingDataListener.OnFetchingData(response.body().getLocation(),response.body().getCurrent(),response.body().getForecast(), response.message());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                onFetchingDataListener.OnError("Request Failed !!!");
            }
        });


    }

    public interface CallWeatherAPI{
        @GET("forecast.json")
        Call<APIResponse> callWeather(
                @Query("key") String key,
                @Query("q") String query,
                @Query("days") String days

        );
    }
}
