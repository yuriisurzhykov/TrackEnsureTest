package com.yuriysurzhikov.trackensuretest.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;

import java.util.List;

public class RefuelingRecyclerAdapter extends RecyclerView.Adapter<RefuelingRecyclerAdapter.RefuelingViewHolder> {

    private List<Refueling> list;
    private OnStationLongClickListener longClickListener;
    private static final String TAG = "RefuelingRecyclerAdapte";

    private RefuelingRecyclerAdapter(OnStationLongClickListener listener) {
        this.longClickListener = listener;
    }

    public static RefuelingRecyclerAdapter getInstance(OnStationLongClickListener listener) {
        return new RefuelingRecyclerAdapter(listener);
    }

    @NonNull
    @Override
    public RefuelingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gas_station_view, parent, false);
        return new RefuelingViewHolder(view, longClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RefuelingViewHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setRefuelingList(List<Refueling> refuelingList) {
        list = refuelingList;
    }

    public static class RefuelingViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private View view;
        private OnStationLongClickListener clickListener;
        private TextView titleTextView;
        private TextView fuelTypeTextView;
        private TextView fuelAmountTextView;
        private TextView costTextView;

        public RefuelingViewHolder(@NonNull View itemView, OnStationLongClickListener clickListener) {
            super(itemView);
            this.view = itemView;
            this.clickListener = clickListener;
            view.setOnLongClickListener(this);
            titleTextView = view.findViewById(R.id.gas_station_name);
            fuelTypeTextView = view.findViewById(R.id.fuel_type);
            fuelAmountTextView = view.findViewById(R.id.fuel_amount);
            costTextView = view.findViewById(R.id.cost);
        }

        public void bindData(Refueling refueling) {
            titleTextView.setText(refueling.getProviderCreator());
            fuelTypeTextView.setText(refueling.getFuelType());
            fuelAmountTextView.setText(String.valueOf(refueling.getFuelAmount()));
            costTextView.setText(String.valueOf(refueling.getCost()));
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onLongClickListener(getAdapterPosition());
            return false;
        }
    }

    public interface OnStationLongClickListener {
        void onLongClickListener(int position);
    }
}
