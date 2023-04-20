package com.example.my_weatherapp.Interface;

import com.example.my_weatherapp.Model.Current;
import com.example.my_weatherapp.Model.ForeCast.ForeCast;
import com.example.my_weatherapp.Model.Location;

import java.util.List;

public interface OnFetchingDataListener<APIResponse> {
    void OnFetchingData(Location location, Current current, ForeCast foreCast, String message);
    void OnError(String message);
}
