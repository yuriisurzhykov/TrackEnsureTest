package com.yuriysurzhikov.trackensuretest.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuriysurzhikov.trackensuretest.R;

public class StationsFragment extends ProjectFragment {

    private static final String ARG_TAB = "ARG_TAB";
    private Context context;
    private View view;

    private StationsFragment(Context context) {
        this.context = context;
    }


    public static StationsFragment getInstance(Context context) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(ARG_TAB, "Gas stations fragment");
        StationsFragment fragment = new StationsFragment(context);
        fragment.setArguments(bundle);
        return new StationsFragment(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stations_layout_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView view1 = view.findViewById(R.id.text);
        if(savedInstanceState != null)
            view1.setText(savedInstanceState.getCharSequence(ARG_TAB));
    }
}
