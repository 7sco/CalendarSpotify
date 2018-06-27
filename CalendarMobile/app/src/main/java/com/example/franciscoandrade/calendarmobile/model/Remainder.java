package com.example.franciscoandrade.calendarmobile.model;


public class Remainder {

    private String title;
    private String startTime;
    private String endTime;

    public Remainder() {
    }

    public Remainder(String title, String satartTime, String endTime) {
        this.title = title;
        this.startTime = satartTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSatartTime() {
        return startTime;
    }

    public void setSatartTime(String satartTime) {
        this.startTime = satartTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
