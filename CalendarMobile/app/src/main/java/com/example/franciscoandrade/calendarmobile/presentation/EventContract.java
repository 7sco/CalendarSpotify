package com.example.franciscoandrade.calendarmobile.presentation;

import android.app.TimePickerDialog;

import com.example.franciscoandrade.calendarmobile.model.Remainder;

import java.util.List;

public interface EventContract {

    interface View{
        void setRecyclerView(List<Remainder> remainderList);
        void setRecyclerViewEmpty();
        void onEventAdded();
        void addRemainderToList(Remainder remainder);
        void showToast(String pick_a_different_end_time);
        void setEndTimeTextView(String endTime);
        void setStartTimeTV(String startTime);
        void showTimeDialog(TimePickerDialog.OnTimeSetListener onTimeSetListener, int hour, int minute, boolean is24Hour);

        void addEventVisible();
    }

    interface Presenter{
        void getEventsList(int dayNumber);
        void addEvent(String title, String timeStart, String timeEnd);
        void setStartTime();
        void setEndTime();
    }

}
