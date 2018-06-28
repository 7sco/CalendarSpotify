package com.example.franciscoandrade.calendarmobile.presentation.interfaces;

import com.example.franciscoandrade.calendarmobile.data.model.CalendarResponse;

import java.util.List;

public interface CalendarContract {

    interface View{
        void setRecyclerView(List<CalendarResponse> monthDaysList, String month);
        void setYear(String year);
        void setMonth(String month);
        void showMessage(String message);
    }

    interface Presenter{
        void getDaysList();

    }
}
