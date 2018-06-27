package com.example.franciscoandrade.calendarmobile.presentation.presenter;

import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TimePicker;
import com.example.franciscoandrade.calendarmobile.model.Day;
import com.example.franciscoandrade.calendarmobile.model.Remainder;
import com.example.franciscoandrade.calendarmobile.presentation.EventContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Calendar;
import java.util.List;

public class EventActivityPresenter implements EventContract.Presenter {

    private static final String TAG= EventActivityPresenter.class.getSimpleName();
    private EventContract.View view;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference tutorialRef;
    private int startMinutes;
    private int startHour;
    private int endMinutes;
    private int endHour;
    int remainderTotal ;

    public EventActivityPresenter(EventContract.View view) {
        this.view = view;
    }

    @Override
    public void getEventsList(int dayNumber) {
        dayNumber=dayNumber-1;
        database.goOnline();
        tutorialRef = database.getReference("Calendar").child("2018").child("0").child("dayList").child(String.valueOf(dayNumber));
        tutorialRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Day day = dataSnapshot.getValue(Day.class);
                setListDays(day);
                view.addEventVisible();
                if (day.getDayDetailsList().getListRemainders()==null){
                    tutorialRef.child("dayDetailsList").child("size").setValue(0);
                }else {
                    tutorialRef.child("dayDetailsList").child("size").setValue(day.getDayDetailsList().getListRemainders().size());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: "+databaseError.getMessage());
                view.showToast("Canceled");
            }
        });
    }

    private void setListDays(Day day) {
        if (day.getDayDetailsList().getListRemainders() != null) {
            List<Remainder> listRemainders = day.getDayDetailsList().getListRemainders();
            remainderTotal=listRemainders.size();
            view.setRecyclerView(listRemainders);

        } else {
            remainderTotal=0;
            view.setRecyclerViewEmpty();
        }
    }

    @Override
    public void addEvent(String title, String timeStart, String timeEnd) {
        Remainder remainder = new Remainder();
        remainder.setSatartTime(timeStart);
        remainder.setEndTime(timeEnd);
        remainder.setTitle(title);
        if(remainder.getTitle().equals("")){
            view.showToast("Please add a title");
        }
        else {
            tutorialRef.child("dayDetailsList").child("listRemainders").child(String.valueOf(remainderTotal)).setValue(remainder);
            remainderTotal= remainderTotal+1;
            view.addRemainderToList(remainder);
            view.onEventAdded();
        }

    }

    @Override
    public void setStartTime() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                StringBuilder builder=appendTime(hour, minute);
                startHour=hour;
                startMinutes=minute;
                if(endHour>hour  ){
                    view.showToast("Pick a different End Time");
                }
                else if(endHour==hour && endMinutes>minute){
                    view.showToast("Pick a different End Time");
                }
                else {
                    view.setEndTimeTextView(builder.toString());
                }
                view.setStartTimeTV(builder.toString());
            }
        };

        setTimeDialog(onTimeSetListener);
    }

    @Override
    public void setEndTime() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                StringBuilder builder=appendTime(hour, minute);
                endHour=hour;
                endMinutes=minute;
                if(hour<startHour  ){
                    view.showToast("Pick a different End Time");
                }
                else if(hour==startHour && minute<startMinutes){
                    view.showToast("Pick a different End Time");
                }
                else {
                    view.setEndTimeTextView(builder.toString());
                }
            }
        };

        setTimeDialog(onTimeSetListener);

    }

    private StringBuilder appendTime(int hour, int minute) {
        return new StringBuilder(hour+":"+minute);
    }

    private void setTimeDialog(TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        boolean is24Hour = true;
        view.showTimeDialog(onTimeSetListener, hour, minute, is24Hour);
    }


}
