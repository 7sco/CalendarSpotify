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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franciscoandrade.calendarmobile.R;
import com.example.franciscoandrade.calendarmobile.model.Day;
import com.example.franciscoandrade.calendarmobile.model.DayDetails;
import com.example.franciscoandrade.calendarmobile.model.Month;
import com.example.franciscoandrade.calendarmobile.model.Remainder;
import com.example.franciscoandrade.calendarmobile.presentation.CalendarContract;
import com.example.franciscoandrade.calendarmobile.presentation.LaunchActivityInterface;
import com.example.franciscoandrade.calendarmobile.presentation.presenter.CalendarActivityPresenter;
import com.example.franciscoandrade.calendarmobile.presentation.recyclerView.CalendarAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity implements CalendarContract.View, LaunchActivityInterface {

    @BindView(R.id.year)
    TextView yearTV;
    @BindView(R.id.month)
    TextView monthTV;
    @BindView(R.id.calendar_rv)
    RecyclerView calendarRv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.noInternet_btn)
    Button noInternetBtn;

    private CalendarAdapter adapter;
    private List<Month> listMonth;
    private int currentMonthNumber;
    private int currentMonthDay;
    private int currentMonthMaxDays;
    private List<Day> listDay;
    private List<DayDetails> dayDetailsList;
    private List<Remainder> remainderList;
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private int counter = 5;
    List<Day> listDays;
    CalendarActivityPresenter presenter = new CalendarActivityPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        //getDataOffline();
        getDataFirebase();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                int size= bundle.getInt("size");
                int dayNumber= (bundle.getInt("dayNumber")-1);
                Log.d("==", "onActivityResult: #: "+dayNumber);
                Log.d("==", "onActivityResult: "+listDays.get(dayNumber).getDayNumber());
                Log.d("==", "onActivityResult: "+listDays.get(dayNumber).getDayDetailsList().getsize());
                listDays.get(dayNumber).getDayDetailsList().setsize(size);
                Log.d("==", "onActivityResult: "+listDays.get(dayNumber).getDayDetailsList().getsize());
                adapter.notifyItemChanged(dayNumber, null);

            }
        }
    }

    private void getDataFirebase() {
        if (isNetworkAvailable()) {
            presenter.getDaysList();
            noInternetBtn.setVisibility(View.INVISIBLE);
        } else {
            noInternetBtn.setVisibility(View.VISIBLE);
            showMessage("No Connection");
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Override
    public void setRecyclerView(List<Day> monthDaysList, String month) {

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
            getDataFirebase();

    }


    private void getDataOffline() {
        //createDataForCalendar();
    }

//    private void createDataForCalendar() {
//        getCalendarInfo();
//        remainderList.add(new Remainder("Remainder Title", "5:00", "6:00"));
//        fillDetailsList();
//        listMonth = new ArrayList<>();
//        fillListMonth();
//        Year year = new Year();
//        year.setMonthsList(listMonth);
//        year.setYearNumber(2018);
//        yearTV.setText("2018 / ");
//        monthTV.setText("January");
//    }

//    private void setRecycler() {
//        adapter = new CalendarAdapter(listDay);
//        calendarRv.setAdapter(adapter);
//        calendarRv.setHasFixedSize(false);
//        calendarRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
//        calendarRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//
//    }

    private void getCalendarInfo() {
        Calendar mycal = Calendar.getInstance();
        currentMonthNumber = mycal.get(Calendar.MONTH);
        currentMonthDay = mycal.get(Calendar.DAY_OF_MONTH);
        currentMonthMaxDays = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        remainderList = new ArrayList<>();
    }

    private void fillListMonth() {
        fillListDay();
        Month month = new Month();
        month.setMonth(months[5]);
        month.setDayList(listDay);
        listMonth.add(month);
    }

    private void fillDetailsList() {
        dayDetailsList = new ArrayList<>();
        for (int i = 0; i < weekDays.length; i++) {
            dayDetailsList.add(new DayDetails(weekDays[i], remainderList, 0));
        }
    }

    private void fillListDay() {
        listDay = new ArrayList<>();
        for (short i = 1; i <= currentMonthMaxDays; i++) {
            Day day = new Day();
            day.setDayNumber(i);
            if (counter > 6) {
                counter = 0;
            }
            day.setDayDetailsList(dayDetailsList.get(counter));
            listDay.add(day);
            counter++;
        }

    }


    @Override
    public void passData(String weekDay, int dayNumber, int reminderTotal, String month) {
        Intent view = new Intent(this, EventsActivity.class);
        view.putExtra("weekDay", weekDay);
        view.putExtra("dayNumber", dayNumber);
        view.putExtra("remainderTotal", reminderTotal);
        view.putExtra("month", month);
        startActivityForResult(view, 1);
    }
}
