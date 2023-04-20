package com.example.my_weatherapp.Model.ForeCast;

public class Hour {
    String temp_c,time;
    ConditionHour condition;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public ConditionHour getCondition() {
        return condition;
    }

    public void setCondition(ConditionHour condition) {
        this.condition = condition;
    }
}
