package com.yuriysurzhikov.trackensuretest.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.model.LatLng;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.presenter.AddingActivityPresenter;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.AddingActivityContract;
import com.yuriysurzhikov.trackensuretest.view.dialogs.AddingBottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddingActivity extends AppCompatActivity implements AddingActivityContract.View, OnMapReadyCallback {

    private final String BOTTOM_SHEET_TAG = "bottom_sheet_dialog";

    private AddingActivityContract.Presenter presenter;
    private SupportMapFragment mapFragment;
    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_layout);
        init();
    }

    private void init() {
        presenter = new AddingActivityPresenter(this, this);
    }


    @Override
    public void openBottomSheet(View view) {
        presenter.createLocation(new LatLng());
        AddingBottomSheetDialog dialog = new AddingBottomSheetDialog(this);
        dialog.show(getSupportFragmentManager(), BOTTOM_SHEET_TAG);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        presenter.onMapReady(googleMap);
    }
}
