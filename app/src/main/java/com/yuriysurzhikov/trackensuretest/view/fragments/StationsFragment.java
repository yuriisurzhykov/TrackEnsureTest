package com.yuriysurzhikov.trackensuretest.view.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.ArraySet;
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
import com.yuriysurzhikov.trackensuretest.model.MainRepositoryContract;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.model.roomRepository.RoomDataProvider;
import com.yuriysurzhikov.trackensuretest.presenter.StationsFragmentPresenter;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.StationsFragmentContract;
import com.yuriysurzhikov.trackensuretest.view.adapters.RefuelingRecyclerAdapter;


import java.util.ArrayList;
import java.util.Set;

public class StationsFragment
        extends ProjectFragment
        implements RefuelingRecyclerAdapter.OnStationLongClickListener, StationsFragmentContract.View {

    private static final String TAG = "StationsFragment";

    private static final String ARG_TAB = "ARG_TAB";
    private Context context;
    private View view;
    private Set<Refueling> gasStations;
    private MainRepositoryContract repository;

    private StationsFragmentContract.Presenter presenter;
    private RecyclerView recyclerView;
    private RefuelingRecyclerAdapter recyclerAdapter;

    private StationsFragment(Context context) {
        this.context = context;
        repository = RoomDataProvider.getInstance(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            gasStations = new ArraySet<>();
        }
        presenter = StationsFragmentPresenter.getInstance(getActivity(), this);
    }


    public static StationsFragment getInstance(Context context) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(ARG_TAB, "Gas stations fragment");
        StationsFragment fragment = new StationsFragment(context);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stations_layout_fragment, container, false);
        repository.getAllStations().observe(getViewLifecycleOwner(), refuelings -> {
            gasStations.addAll(refuelings);
            Log.d(TAG, "onCreateView: " + refuelings.size());
            recyclerAdapter = RefuelingRecyclerAdapter.getInstance(context, this);
            recyclerAdapter.setRefuelingList(new ArrayList<>(gasStations));
            recyclerView = view.findViewById(R.id.stations_recycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
        });
        Log.d(TAG, "onCreateView: " + gasStations);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView view1 = view.findViewById(R.id.text);
        if (savedInstanceState != null)
            view1.setText(savedInstanceState.getCharSequence(ARG_TAB));
    }

    @Override
    public void onLongClickListener(int position) {

    }
}
