package com.example.franciscoandrade.calendarmobile.presentation.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franciscoandrade.calendarmobile.R;
import com.example.franciscoandrade.calendarmobile.data.model.PostRemainder;
import com.example.franciscoandrade.calendarmobile.data.model.Remainder;
import com.example.franciscoandrade.calendarmobile.presentation.interfaces.DeleteRemainderInterface;
import com.example.franciscoandrade.calendarmobile.presentation.interfaces.LaunchActivityInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Remainder> listRemainders;
    DeleteRemainderInterface deleteInterface;

    public EventAdapter(List<Remainder> listRemainders, DeleteRemainderInterface deleteInterface) {
        this.listRemainders = listRemainders;
        this.deleteInterface= deleteInterface;
    }

    public EventAdapter(DeleteRemainderInterface deleteInterface) {
        listRemainders= new ArrayList<>();
        this.deleteInterface= deleteInterface;
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

    public void deleteRemainder(int position) {
        listRemainders.remove(position);
        notifyItemRemoved(position);
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title_tv)
        TextView titleTv;
        @BindView(R.id.time_tv)
        TextView timeTv;
        private String id;


        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void onBind(Remainder remainder) {
            Log.d("==", "onBind: ID: "+remainder.getId());
            Log.d("==", "onBind: "+getAdapterPosition());
            id=remainder.getId();
            if (remainder!= null && remainder.getEndtime()!=null){
                String timePeriod;
                if(remainder.getStarttime().equals("") && remainder.getEndtime().equals("")){
                    timePeriod= "All Day";
                }else {
                    timePeriod=remainder.getStarttime()+" / "+remainder.getEndtime();
                }
                titleTv.setText(remainder.getTitle());
                timeTv.setText(timePeriod);
            }
        }

        @Override
        public void onClick(View v) {
            deleteInterface.passData(id, getAdapterPosition());

        }
    }
}
