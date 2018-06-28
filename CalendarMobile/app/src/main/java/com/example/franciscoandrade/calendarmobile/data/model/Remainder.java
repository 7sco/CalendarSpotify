package com.example.franciscoandrade.calendarmobile.data.model;

import com.google.gson.annotations.SerializedName;

public class Remainder {
    @SerializedName("__v")
    private int V;
    @SerializedName("dayRemainder")
    private String dayremainder;
    @SerializedName("endTime")
    private String endtime;
    @SerializedName("startTime")
    private String starttime;
    @SerializedName("title")
    private String title;
    @SerializedName("_id")
    private String Id;

    public int getV() {
        return V;
    }

    public String getDayremainder() {
        return dayremainder;
    }

    public void setV(int v) {
        V = v;
    }

    public void setDayremainder(String dayremainder) {
        this.dayremainder = dayremainder;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return Id;
    }

}
