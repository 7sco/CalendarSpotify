package com.example.franciscoandrade.calendarmobile.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franciscoandrade.calendarmobile.R;
import com.example.franciscoandrade.calendarmobile.data.api.ClientService;
import com.example.franciscoandrade.calendarmobile.data.model.CalendarResponse;
import com.example.franciscoandrade.calendarmobile.presentation.interfaces.CalendarContract;
import com.example.franciscoandrade.calendarmobile.presentation.interfaces.LaunchActivityInterface;
import com.example.franciscoandrade.calendarmobile.presentation.presenter.CalendarActivityPresenter;
import com.example.franciscoandrade.calendarmobile.presentation.recyclerView.CalendarAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity implements CalendarContract.View, LaunchActivityInterface {

    @BindView(R.id.year) TextView yearTV;
    @BindView(R.id.month) TextView monthTV;
    @BindView(R.id.calendar_rv) RecyclerView calendarRv;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.noInternet_btn) Button noInternetBtn;

    private CalendarAdapter adapter;
    private List<CalendarResponse> listDays;
    private ClientService clientService = new ClientService("https://calendarandroid.herokuapp.com");
    private CalendarActivityPresenter presenter = new CalendarActivityPresenter(this,clientService);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
    }

    private void getData() {
        if (isNetworkAvailable()) {
            presenter.getDaysList();
            noInternetBtn.setVisibility(View.INVISIBLE);
        } else {
            noInternetBtn.setVisibility(View.VISIBLE);
            showMessage(getResources().getString(R.string.No_Connection));
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Override
    public void setRecyclerView(List<CalendarResponse> monthDaysList, String month) {

        adapter = new CalendarAdapter( monthDaysList, month, this);
        listDays= new ArrayList<>();
        listDays.addAll(monthDaysList);
        calendarRv.setAdapter(adapter);
        calendarRv.setHasFixedSize(false);
        calendarRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        calendarRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setYear(String year) {
        yearTV.setText(year);
    }

    @Override
    public void setMonth(String month) {
        monthTV.setText(month);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.noInternet_btn)
    public void onViewClicked() {
            getData();

    }

    @Override
    public void passData(String id,String weekDay, int monthDay , String month) {
        Intent view = new Intent(this, EventsActivity.class);
        view.putExtra(getResources().getString(R.string.id), id);
        view.putExtra(getResources().getString(R.string.week_day), weekDay);
        view.putExtra(getResources().getString(R.string.month_day), monthDay);
        view.putExtra(getResources().getString(R.string.month), month);
        startActivityForResult(view, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }


}
