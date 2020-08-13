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
    private Context context;
    private OnStationLongClickListener longClickListener;
    private static final String TAG = "RefuelingRecyclerAdapte";

    private RefuelingRecyclerAdapter(Context context, OnStationLongClickListener listener) {
        this.context = context;
    }

    public static RefuelingRecyclerAdapter getInstance(Context context, OnStationLongClickListener listener) {
        return new RefuelingRecyclerAdapter(context, listener);
    }

    @NonNull
    @Override
    public RefuelingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gas_station_view, parent, false);
        return new RefuelingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RefuelingViewHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + list.size());
        return list.size();
    }

    public void setRefuelingList(List<Refueling> refuelingList) {
        Log.d(TAG, "setRefuelingList: " + refuelingList.size());
        list = refuelingList;
    }

    public class RefuelingViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView titleTextView;
        private TextView fuelTypeTextView;
        private TextView fuelAmountTextView;
        private TextView costTextView;

        public RefuelingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            titleTextView = view.findViewById(R.id.gas_station_name);
            fuelTypeTextView = view.findViewById(R.id.fuel_type);
            fuelAmountTextView = view.findViewById(R.id.fuel_amount);
            costTextView = view.findViewById(R.id.cost);
        }

        public void bindData(Refueling refueling) {
            titleTextView.setText(refueling.getProvider());
            fuelTypeTextView.setText(refueling.getFuelType());
            fuelAmountTextView.setText(String.valueOf(refueling.getFuelAmount()));
            costTextView.setText(String.valueOf(refueling.getCost()));
        }
    }

    public interface OnStationLongClickListener {
        void onLongClickListener(int position);
    }
}
