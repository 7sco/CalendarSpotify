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
