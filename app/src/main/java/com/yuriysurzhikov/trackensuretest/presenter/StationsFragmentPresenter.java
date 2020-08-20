package com.yuriysurzhikov.trackensuretest.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.yuriysurzhikov.trackensuretest.model.MainRepository;
import com.yuriysurzhikov.trackensuretest.model.entities.Place;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.StationsFragmentContract;
import com.yuriysurzhikov.trackensuretest.view.activities.AddingActivity;
import com.yuriysurzhikov.trackensuretest.view.activities.EditingActivity;

import org.jetbrains.annotations.NotNull;

import static com.yuriysurzhikov.trackensuretest.view.fragments.StationsFragment.ARG_TAB;

public class StationsFragmentPresenter implements StationsFragmentContract.Presenter {

    private static final String TAG = "StationsFragmentPresen";
    private Activity activity;
    private StationsFragmentContract.View view;
    private StationsFragmentPresenter(StationsFragmentContract.View view) {
        this.activity = activity;
        this.view = view;
    }

    public static StationsFragmentPresenter getInstance(StationsFragmentContract.View view) {
        StationsFragmentPresenter presenter = new StationsFragmentPresenter(view);
        return presenter;
    }

    @Override
    public void deleteRefuelingNote(@NotNull Refueling refueling) {
        MainRepository.getInstance().deleteRefuelingNote(refueling);
    }

    @Override
    public void openEditingActivity(@NotNull Refueling refueling, @NotNull Place place, @NotNull Context context) {
        Bundle bundleRefueling = new Bundle();
        bundleRefueling.putString(ARG_TAB + "refueling", new Gson().toJson(refueling));
        Bundle bundlePlace = new Bundle();
        bundlePlace.putString( ARG_TAB + "place", new Gson().toJson(place));
        Intent intent = new Intent(context, EditingActivity.class);
        intent.putExtras(bundleRefueling);
        intent.putExtras(bundlePlace);
        context.startActivity(intent);
    }

    @Override
    public void openAddingActivity(@NotNull Context context) {
        Intent intent = new Intent(context, AddingActivity.class);
        context.startActivity(intent);
    }
}
