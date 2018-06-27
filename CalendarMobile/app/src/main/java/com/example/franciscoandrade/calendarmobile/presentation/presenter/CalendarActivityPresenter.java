package com.example.franciscoandrade.calendarmobile.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.franciscoandrade.calendarmobile.model.Day;
import com.example.franciscoandrade.calendarmobile.model.Month;
import com.example.franciscoandrade.calendarmobile.presentation.CalendarContract;
import com.example.franciscoandrade.calendarmobile.presentation.view.CalendarActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

public class CalendarActivityPresenter implements CalendarContract.Presenter {

    private static final String TAG = CalendarActivityPresenter.class.getSimpleName();
    private CalendarContract.View view;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public CalendarActivityPresenter(CalendarActivity calendarActivity) {
        view= calendarActivity;
    }

    @Override
    public void getDaysList() {

        DatabaseReference tutorialRef = database.getReference("Calendar").child("2018").child("0");
        view.setYear(tutorialRef.getParent().getKey());
        tutorialRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Month month= dataSnapshot.getValue(Month.class);
                    List<Day> listDay= month.getDayList();
                    view.setRecyclerView(listDay, month.getMonth());
                    view.setMonth(month.getMonth()+"/");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: "+databaseError.getMessage());
                view.showMessage("Error Loading");
            }
        });



//        tutorialRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }



}
