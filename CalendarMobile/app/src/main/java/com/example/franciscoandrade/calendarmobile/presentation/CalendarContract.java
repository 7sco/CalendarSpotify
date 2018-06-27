package com.example.franciscoandrade.calendarmobile.presentation;

import com.example.franciscoandrade.calendarmobile.model.Day;

import java.util.List;

public interface CalendarContract {

    interface View{
        void setRecyclerView(List<Day> monthDaysList, String month);
        void setYear(String year);
        void setMonth(String month);
        void showMessage(String message);
    }

    interface Presenter{
        void getDaysList();

    }
}
