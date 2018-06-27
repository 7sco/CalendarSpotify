package com.example.franciscoandrade.calendarmobile.model;

import java.util.List;

public class Month {

    private String month;
    private List<Day> dayList;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }
}