package com.example.franciscoandrade.calendarmobile.model;

import java.util.List;

public class DayDetails {
    private String weekDay;
    private List<Remainder> listRemainders;
    private int size;

    public DayDetails() {
    }

    public DayDetails(String weekDay, List<Remainder> listRemainders, int size) {
        this.weekDay = weekDay;
        this.listRemainders = listRemainders;
        this.size = size;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public List<Remainder> getListRemainders() {
        return listRemainders;
    }

    public void setListRemainders(List<Remainder> listRemainders) {
        this.listRemainders = listRemainders;
    }


    public int getsize() {
        return size;
    }

    public void setsize(int size) {
        this.size = size;
    }
}

