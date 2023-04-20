package com.example.my_weatherapp.Model.ForeCast;

import android.hardware.lights.LightState;

import java.util.List;

public class ForeCastDay {
    String date;
    List<Hour>hour;
    Day day;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public List<Hour> getHour() {
        return hour;
    }

    public void setHour(List<Hour> hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
