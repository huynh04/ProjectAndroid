package com.example.my_weatherapp.Model;

import com.example.my_weatherapp.Model.ForeCast.ForeCast;

import java.util.List;

public class APIResponse {
    Location location;
    Current current;
    ForeCast forecast;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public ForeCast getForecast() {
        return forecast;
    }

    public void setForecast(ForeCast forecast) {
        this.forecast = forecast;
    }
}
