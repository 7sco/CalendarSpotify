package com.example.franciscoandrade.calendarmobile.data.model;

import com.google.gson.annotations.SerializedName;

public class PostRemainder {

    @SerializedName("endTime")
    private String endtime;
    @SerializedName("startTime")
    private String starttime;
    @SerializedName("title")
    private String title;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
