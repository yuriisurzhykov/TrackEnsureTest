package com.yuriysurzhikov.trackensuretest.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.customView.StatisticsView;
import com.yuriysurzhikov.trackensuretest.model.entities.StatisticsLive;
import com.yuriysurzhikov.trackensuretest.model.entities.StatisticsStatic;

import java.util.List;

public class StatisticsRecyclerAdapter extends RecyclerView.Adapter<StatisticsRecyclerAdapter.StatisticsViewHolder> {

    private static final String TAG = "StatisticsRecyclerAdapt";

    private View view;
    private List<StatisticsLive> statistics;

    public StatisticsRecyclerAdapter(Context context, List<StatisticsLive> statistics) {
        this.statistics = statistics;
    }

    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistics_item, parent, false);
        return new StatisticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsViewHolder holder, int position) {
        holder.bind(statistics.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + statistics.size());
        return statistics.size();
    }

    static class StatisticsViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private StatisticsView statisticsView;

        public StatisticsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            statisticsView = view.findViewById(R.id.statisticsView);
        }

        public void bind(StatisticsLive statisticsLive) {
            statisticsLive.getElements().observeForever(statisticsElements -> {
                StatisticsStatic aStatic = new StatisticsStatic(statisticsLive.getProvider(), statisticsLive.getAddress(), statisticsElements);
                statisticsView.setStatisticsLive(aStatic);
            });
        }
    }
}
