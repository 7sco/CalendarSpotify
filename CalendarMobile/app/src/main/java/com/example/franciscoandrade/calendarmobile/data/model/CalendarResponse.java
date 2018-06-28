package com.example.franciscoandrade.calendarmobile.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalendarResponse {

    @SerializedName("__v")
    private int V;
    @SerializedName("monthDay")
    private int monthday;
    @SerializedName("weekDay")
    private String weekday;
    @SerializedName("month")
    private String month;
    @SerializedName("_id")
    private String Id;
    @SerializedName("remainder")
    private List<String> remainder;

    public int getV() {
        return V;
    }

    public int getMonthday() {
        return monthday;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getMonth() {
        return month;
    }

    public String getId() {
        return Id;
    }

    public List<String> getRemainder() {
        return remainder;
    }

}
