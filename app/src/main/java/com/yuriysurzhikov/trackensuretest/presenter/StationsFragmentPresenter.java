package com.yuriysurzhikov.trackensuretest.presenter;

import android.app.Activity;
import android.content.Context;

import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.StationsFragmentContract;

import org.jetbrains.annotations.NotNull;

public class StationsFragmentPresenter implements StationsFragmentContract.Presenter {

    private Activity activity;
    private StationsFragmentContract.View view;
    private StationsFragmentPresenter(Activity activity, StationsFragmentContract.View view) {
        this.activity = activity;
        this.view = view;
    }

    public static StationsFragmentPresenter getInstance(Activity activity, StationsFragmentContract.View view) {
        StationsFragmentPresenter presenter = new StationsFragmentPresenter(activity, view);
        return presenter;
    }
    @Override
    public void openAddingActivity(@NotNull Context context) {

    }

    @Override
    public void deleteRefuelingNote(@NotNull Refueling refueling) {

    }

    @Override
    public void updateRefuelingNote(@NotNull Refueling refueling) {

    }
}
