package com.example.franciscoandrade.calendarmobile.presentation.recyclerView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franciscoandrade.calendarmobile.R;
import com.example.franciscoandrade.calendarmobile.model.Day;
import com.example.franciscoandrade.calendarmobile.presentation.LaunchActivityInterface;
import com.example.franciscoandrade.calendarmobile.presentation.view.EventsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    List<Day> listDay;
    String month;
    LaunchActivityInterface launcActivity;

    public CalendarAdapter(List<Day> listDay, String month, LaunchActivityInterface launchActivityInterface) {
        this.listDay = listDay;
        this.month=month;
        launcActivity= launchActivityInterface;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_itemview, parent, false);
        return new CalendarViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        Day day = listDay.get(position);
        holder.bind(day);


    }

    @Override
    public int getItemCount() {
        return listDay.size();
    }



    public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.day)
        TextView dayTV;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.remainders)
        TextView remainders;
        @BindView(R.id.remainders_line)
        TextView remainders_line;


        Day itemViewDay;


        public CalendarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        public void bind(Day day) {
            itemViewDay = day;
            dayTV.setText(day.getDayDetailsList().getWeekDay());
            number.setText(day.getDayNumber() + "");
            if (day.getDayDetailsList().getListRemainders() != null) {
                remainders.setVisibility(View.VISIBLE);
                remainders.setText(day.getDayDetailsList().getsize()+ "");
                remainders_line.setVisibility(View.VISIBLE);

            }

        }

        @Override
        public void onClick(View v) {
            int remainderTotal;
            if (itemViewDay.getDayDetailsList().getListRemainders() == null) {
                remainderTotal = 0;
            } else {
                remainderTotal = itemViewDay.getDayDetailsList().getListRemainders().size();
            }

            launcActivity.passData(itemViewDay.getDayDetailsList().getWeekDay()
                    ,itemViewDay.getDayNumber()
                    ,remainderTotal
                    ,month);

        }

    }
}
