package com.example.franciscoandrade.calendarmobile.presentation.presenter;

import android.util.Log;

import com.example.franciscoandrade.calendarmobile.data.api.CalendarApi;
import com.example.franciscoandrade.calendarmobile.data.api.ClientService;
import com.example.franciscoandrade.calendarmobile.data.model.CalendarResponse;
import com.example.franciscoandrade.calendarmobile.presentation.interfaces.CalendarContract;
import com.example.franciscoandrade.calendarmobile.presentation.view.CalendarActivity;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarActivityPresenter implements CalendarContract.Presenter {

    private static final String TAG = CalendarActivityPresenter.class.getSimpleName();
    private CalendarContract.View view;
    private ClientService clientService;

    public CalendarActivityPresenter(CalendarActivity calendarActivity, ClientService clientService) {
        view= calendarActivity;
        this.clientService = clientService;
    }

    @Override
    public void getDaysList() {
        CalendarApi calendarApi= clientService.getCalendarApi();
        Call<List<CalendarResponse>> calendarCall= calendarApi.getDaysList();
        calendarCall.enqueue(new Callback<List<CalendarResponse>>() {
            @Override
            public void onResponse(Call<List<CalendarResponse>> call, Response<List<CalendarResponse>> response) {
                view.setRecyclerView(response.body(),response.body().get(0).getMonth());
                view.setYear("2018");
                if(response.body()!=null){
                    view.setMonth(response.body().get(0).getMonth()+"/");
                }
            }

            @Override
            public void onFailure(Call<List<CalendarResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
