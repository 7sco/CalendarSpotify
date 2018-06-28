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

    public void setV(int V) {
        this.V = V;
    }

    public int getMonthday() {
        return monthday;
    }

    public void setMonthday(int monthday) {
        this.monthday = monthday;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public List<String> getRemainder() {
        return remainder;
    }

    public void setRemainder(List<String> remainder) {
        this.remainder = remainder;
    }
}
