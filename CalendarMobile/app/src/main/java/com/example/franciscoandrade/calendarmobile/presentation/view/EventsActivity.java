package com.example.franciscoandrade.calendarmobile.presentation.view;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franciscoandrade.calendarmobile.R;
import com.example.franciscoandrade.calendarmobile.data.api.ClientService;
import com.example.franciscoandrade.calendarmobile.data.model.PostRemainder;
import com.example.franciscoandrade.calendarmobile.data.model.Remainder;
import com.example.franciscoandrade.calendarmobile.presentation.interfaces.EventContract;
import com.example.franciscoandrade.calendarmobile.presentation.presenter.EventActivityPresenter;
import com.example.franciscoandrade.calendarmobile.presentation.recyclerView.EventAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventsActivity extends AppCompatActivity implements EventContract.View {

    @BindView(R.id.currentDay) TextView currentDay;
    @BindView(R.id.event_title) EditText eventTitle;
    @BindView(R.id.time_textView) TextView timeTextView;
    @BindView(R.id.pick_Time) Button pickTime;
    @BindView(R.id.addEvent_btn) Button addEventBtn;
    @BindView(R.id.event_info) TextView eventInfo;
    @BindView(R.id.events_rv) RecyclerView eventsRv;
    @BindView(R.id.endtime_textView) TextView endtimeTextView;
    @BindView(R.id.pick_TimeEnd) Button pickTimeEnd;
    @BindView(R.id.retry_button) Button retryButton;

    private EventAdapter adapter;
    private EventContract.Presenter presenter;
    private String id;
    private ClientService clientService = new ClientService("https://calendarandroid.herokuapp.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);

        presenter = new EventActivityPresenter(this, clientService);
        if(savedInstanceState!=null) {
            String title= savedInstanceState.getString(getResources().getString(R.string.title));
            String startTime= savedInstanceState.getString(getResources().getString(R.string.startTime));
            String endTime= savedInstanceState.getString(getResources().getString(R.string.endTime));
            eventTitle.setText(title);
            timeTextView.setText(startTime);
            endtimeTextView.setText(endTime);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (eventTitle.getText() !=null){
            outState.putString(getResources().getString(R.string.title), eventTitle.getText().toString());
        }
        if (timeTextView.getText()!=null) {
            outState.putString(getResources().getString(R.string.startTime), timeTextView.getText().toString());
        }
        if (endtimeTextView.getText()!=null) {
            outState.putString(getResources().getString(R.string.endTime), endtimeTextView.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }


    private void getRemainders() {
        if (isNetworkAvailable()) {
            presenter.getEventsList(id );
            retryButton.setVisibility(View.INVISIBLE);
        } else {
            retryButton.setVisibility(View.VISIBLE);
            addEventBtn.setVisibility(View.INVISIBLE);
            showToast(getResources().getString(R.string.No_Connection));

        }
    }

    private void getIntentData() {
        Intent i = getIntent();
        id = i.getExtras().getString(getResources().getString(R.string.id));
    }


    @OnClick({R.id.pick_Time, R.id.addEvent_btn, R.id.pick_TimeEnd, R.id.retry_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pick_Time:
                presenter.setStartTime();
                break;
            case R.id.addEvent_btn:

                addEvent();

                break;
            case R.id.pick_TimeEnd:
                presenter.setEndTime();
                break;
            case R.id.retry_button:
                getRemainders();
                break;
        }
    }

    private void addEvent() {
        if(isNetworkAvailable()){
            String title = eventTitle.getText().toString();
            String timeStart = timeTextView.getText().toString();
            String timeEnd = endtimeTextView.getText().toString();
            presenter.addEvent(title, timeStart, timeEnd);
        }
        else {
            showToast("No Internet");
        }
    }

    @Override
    public void setRecyclerView(List<Remainder> remainderList) {
        adapter = new EventAdapter(remainderList);
        eventsRv.setAdapter(adapter);
        eventsRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setRecyclerViewEmpty() {
        adapter = new EventAdapter();
        eventsRv.setAdapter(adapter);
    }

    @Override
    public void onEventAdded() {
        eventTitle.setText(null);
        timeTextView.setText(null);
        endtimeTextView.setText(null);
    }


    @Override
    public void addRemainderToList(Remainder remainder) {
        adapter.addRemainder(remainder);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(EventsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEndTimeTextView(String endTime) {
        endtimeTextView.setText(endTime);
    }

    @Override
    public void setStartTimeTV(String startTime) {
        timeTextView.setText(startTime);
    }

    @Override
    public void showTimeDialog(TimePickerDialog.OnTimeSetListener onTimeSetListener, int hour, int minute, boolean is24Hour) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(EventsActivity.this, onTimeSetListener, hour, minute, is24Hour);
        timePickerDialog.setTitle(getResources().getString(R.string.select_time));
        timePickerDialog.show();
    }

    @Override
    public void addEventVisible() {
        addEventBtn.setVisibility(View.VISIBLE);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getIntentData();
        getRemainders();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
