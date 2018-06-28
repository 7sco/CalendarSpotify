package com.example.franciscoandrade.calendarmobile.presentation.presenter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.TimePicker;

import com.example.franciscoandrade.calendarmobile.data.api.CalendarApi;
import com.example.franciscoandrade.calendarmobile.data.api.ClientService;
import com.example.franciscoandrade.calendarmobile.data.model.PostRemainder;
import com.example.franciscoandrade.calendarmobile.data.model.Remainder;
import com.example.franciscoandrade.calendarmobile.presentation.interfaces.EventContract;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivityPresenter implements EventContract.Presenter {

    private static final String TAG= EventActivityPresenter.class.getSimpleName();
    private EventContract.View view;

    private int startMinutes;
    private int startHour;
    private int endMinutes;
    private int endHour;
    private int remainderTotal ;
    private ClientService clientService;
    private String id;

    public EventActivityPresenter(EventContract.View view, ClientService clientService) {
        this.view = view;
        this.clientService = clientService;
    }

    @Override
    public void getEventsList(final String id) {
        this.id=id;
        CalendarApi calendarApi= clientService.getCalendarApi();
        Call<List<Remainder>> calendarCall= calendarApi.getRemainders(id);
        calendarCall.enqueue(new Callback<List<Remainder>>() {
            @Override
            public void onResponse(Call<List<Remainder>> call, Response<List<Remainder>> response) {
                List<Remainder> remaindersList= response.body();
                view.setRecyclerView(remaindersList);
            }

            @Override
            public void onFailure(Call<List<Remainder>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    @Override
    public void addEvent(String title, String timeStart, String timeEnd) {
        Remainder remainder = new Remainder();
        remainder.setStarttime(timeStart);
        remainder.setEndtime(timeEnd);
        remainder.setTitle(title);
        if(remainder.getTitle().equals("") || remainder.getStarttime().equals("") ||remainder.getEndtime().equals("") ){
            view.showToast("Please fill all the fields");
        }
        else {
            postEvent(remainder);
            remainderTotal= remainderTotal+1;
            view.addRemainderToList(remainder);
            view.onEventAdded();
        }

    }

    private void postEvent(Remainder remainder) {
        PostRemainder postRemainder= new PostRemainder();
        postRemainder.setStarttime(remainder.getStarttime());
        postRemainder.setEndtime(remainder.getEndtime());
        postRemainder.setTitle(remainder.getTitle());

        CalendarApi calendarApi= clientService.getCalendarApi();
        Call<PostRemainder> calendarCall= calendarApi.postRemainder(id, postRemainder);
        calendarCall.enqueue(new Callback<PostRemainder>() {
            @Override
            public void onResponse(Call<PostRemainder> call, Response<PostRemainder> response) {
                view.showToast("Added");
            }

            @Override
            public void onFailure(Call<PostRemainder> call, Throwable t) {
                view.showToast("Error Whe Trying to add remainder");
            }
        });

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



    @Override
    public void deletFromDB(String idRemainder) {
        CalendarApi calendarApi= clientService.getCalendarApi();
        Call<PostRemainder> calendarCall= calendarApi.deleteRemainder(idRemainder);
        calendarCall.enqueue(new Callback<PostRemainder>() {
            @Override
            public void onResponse(Call<PostRemainder> call, Response<PostRemainder> response) {
                view.showToast("DELETED!!");

            }

            @Override
            public void onFailure(Call<PostRemainder> call, Throwable t) {
                view.showToast("Cant Delete!");
            }
        });
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
