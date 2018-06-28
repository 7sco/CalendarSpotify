package com.example.franciscoandrade.calendarmobile.presentation.interfaces;

import android.app.TimePickerDialog;
import com.example.franciscoandrade.calendarmobile.data.model.Remainder;
import java.util.List;

public interface EventContract {

    interface View{
        void setRecyclerView(List<Remainder> remainderList);
        void onEventAdded();
        void addRemainderToList(Remainder remainder);
        void showToast(String message);
        void setEndTimeTextView(String endTime);
        void setStartTimeTV(String startTime);
        void showTimeDialog(TimePickerDialog.OnTimeSetListener onTimeSetListener, int hour, int minute, boolean is24Hour);
        void addEventVisible();

    }

    interface Presenter{
        void getEventsList(String id);
        void addEvent(String title, String timeStart, String timeEnd);
        void setStartTime();
        void setEndTime();
        void deletFromDB(String idRemainder);
    }

}
