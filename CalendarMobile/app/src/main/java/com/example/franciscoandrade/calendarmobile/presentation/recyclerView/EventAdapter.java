package com.example.franciscoandrade.calendarmobile.presentation.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franciscoandrade.calendarmobile.R;
import com.example.franciscoandrade.calendarmobile.model.Remainder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    List<Remainder> listRemainders;

    public EventAdapter(List<Remainder> listRemainders) {
        this.listRemainders = listRemainders;
    }

    public EventAdapter() {
        listRemainders= new ArrayList<>();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_itemview, parent, false);
        return new EventViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Remainder remainder = listRemainders.get(position);
        holder.onBind(remainder);
    }

    @Override
    public int getItemCount() {
        return listRemainders.size();
    }

    public void addRemainder(Remainder remainder) {

        listRemainders.add(remainder);
        notifyItemChanged(listRemainders.size()-1);

    }

    public void addRemainderList(ArrayList<Remainder> listRemainder) {
        listRemainders=listRemainder;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_tv)
        TextView titleTv;
        @BindView(R.id.time_tv)
        TextView timeTv;


        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Remainder remainder) {
            if (remainder!= null && remainder.getEndTime()!=null){
                String timePeriod;
                if(remainder.getSatartTime().equals("") && remainder.getEndTime().equals("")){
                    timePeriod= "All Day";
                }else {
                    timePeriod=remainder.getSatartTime()+" / "+remainder.getEndTime();
                }
                titleTv.setText(remainder.getTitle());
                timeTv.setText(timePeriod);
            }


        }
    }
}
