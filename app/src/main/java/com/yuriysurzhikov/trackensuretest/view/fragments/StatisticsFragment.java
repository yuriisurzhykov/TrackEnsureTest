package com.yuriysurzhikov.trackensuretest.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.model.MainRepository;
import com.yuriysurzhikov.trackensuretest.presenter.StatisticsFragmentPresenter;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.StatisticsFragmentContract;
import com.yuriysurzhikov.trackensuretest.view.adapters.StatisticsRecyclerAdapter;

public class StatisticsFragment extends ProjectFragment implements StatisticsFragmentContract.View {

    private static final String TAG = "StatisticsFragment";

    private static final String ARG_TAB = "ARG_TAB";
    private View view;
    private RecyclerView recyclerView;
    private StatisticsRecyclerAdapter recyclerAdapter;
    private StatisticsFragmentContract.Presenter presenter;

    private StatisticsFragment() {
        presenter = new StatisticsFragmentPresenter(this);
    }

    public static StatisticsFragment getInstance() {
        return new StatisticsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.statistics_layout_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView view1 = view.findViewById(R.id.text);
        if (savedInstanceState != null)
            view1.setText(savedInstanceState.getCharSequence(ARG_TAB));
        MainRepository.getInstance().highlightStatistics().observe(getViewLifecycleOwner(), statisticsElements -> {
            Log.d(TAG, "onViewCreated: statisticsElements - " + statisticsElements.size());
            recyclerAdapter = new StatisticsRecyclerAdapter(getContext(), statisticsElements);
            recyclerView = view.findViewById(R.id.statistics_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
        });
    }
}
