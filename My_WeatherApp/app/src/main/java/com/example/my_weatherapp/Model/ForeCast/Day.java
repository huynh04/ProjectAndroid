package com.example.my_weatherapp.Model.ForeCast;

public class Day {
    String maxtemp_c,mintemp_c;
    ConditionDay condition;

    public String getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(String maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public String getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(String mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public ConditionDay getCondition() {
        return condition;
    }

    public void setCondition(ConditionDay condition) {
        this.condition = condition;
    }
}
