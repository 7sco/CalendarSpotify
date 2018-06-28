package com.example.franciscoandrade.calendarmobile.presentation.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.franciscoandrade.calendarmobile.R;
import com.example.franciscoandrade.calendarmobile.data.model.CalendarResponse;
import com.example.franciscoandrade.calendarmobile.presentation.interfaces.LaunchActivityInterface;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private List<CalendarResponse> listDay;
    private LaunchActivityInterface launcActivity;

    public CalendarAdapter(List<CalendarResponse> listDay, String month, LaunchActivityInterface launchActivityInterface) {
        this.listDay = listDay;
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
        CalendarResponse calendar=listDay.get(position);
        holder.bind(calendar);
    }

    @Override
    public int getItemCount() {
        return listDay.size();
    }


    public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.day) TextView dayTV;
        @BindView(R.id.number) TextView number;
        @BindView(R.id.remainders) TextView remainders;
        @BindView(R.id.remainders_line) TextView remainders_line;
        private CalendarResponse itemViewDay;

        public CalendarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(CalendarResponse day) {
            itemViewDay = day;
            dayTV.setText(day.getWeekday());
            number.setText(String.valueOf(day.getMonthday()));
            if (day.getRemainder()!= null) {
                remainders.setVisibility(View.VISIBLE);
                remainders.setText(String.valueOf(day.getRemainder().size()));
                remainders_line.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            launcActivity.passData(itemViewDay.getId());
        }
    }
}
