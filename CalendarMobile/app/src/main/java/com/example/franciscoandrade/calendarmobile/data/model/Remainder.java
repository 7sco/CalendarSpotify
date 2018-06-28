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

    public void setV(int V) {
        this.V = V;
    }

    public String getDayremainder() {
        return dayremainder;
    }

    public void setDayremainder(String dayremainder) {
        this.dayremainder = dayremainder;
    }

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

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
}
