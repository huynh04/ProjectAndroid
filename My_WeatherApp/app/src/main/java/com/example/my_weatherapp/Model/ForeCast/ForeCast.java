package com.example.my_weatherapp.Model.ForeCast;

import java.util.List;

public class ForeCast {
    List<ForeCastDay> forecastday;

    public List<ForeCastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<ForeCastDay> forecastday) {
        this.forecastday = forecastday;
    }
}
