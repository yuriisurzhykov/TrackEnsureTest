package com.yuriysurzhikov.trackensuretest.view.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.gson.Gson;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.presenter.*;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.*;
import com.yuriysurzhikov.trackensuretest.view.dialogs.AddingBottomSheetDialog;

import org.jetbrains.annotations.NotNull;

public class AddingActivity extends AppCompatActivity implements AddingActivityContract.View, OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private final String BOTTOM_SHEET_TAG = "bottom_sheet_dialog";

    private AddingActivityContract.Presenter presenter;
    private AddingBottomSheetDialog dialog;
    private AddingBottomSheetContract.Presenter bottomSheetPresenter;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private Button openDialogButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_layout);
        init();
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void init() {
        presenter = new AddingActivityPresenter(this, this);
        openDialogButton = findViewById(R.id.open_bottom_sheet);
    }


    @Override
    public void openBottomSheet(View view) {
        dialog = new AddingBottomSheetDialog(this);
        dialog.setPresenter(presenter);
        dialog.show(getSupportFragmentManager(), BOTTOM_SHEET_TAG);
    }

    @Override
    public void closeBottomSheet() {
        dialog.dismiss();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnMapLongClickListener(this);
        presenter.onMapReady(googleMap);
    }

    public void finish(@NotNull View view) {
        finish();
    }

    @Override
    public void setNextButtonActive() {
        openDialogButton.setVisibility(View.VISIBLE);
    }


    @Override
    public void onMapLongClick(com.google.android.gms.maps.model.LatLng latLng) {

        presenter.setLocation(latLng);
        setNextButtonActive();
        googleMap.setOnMarkerDragListener(presenter);
    }

    @Override
    public void save(@NotNull View view) {
        presenter.saveRefuelingNote();
    }

    @Override
    public void showError(@NotNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccess(@NotNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void createMarker(@NotNull LatLng latlng) {
        googleMap.clear();
        googleMap.addMarker(
                new MarkerOptions()
                        .position(latlng)
                        .title(presenter.getModelPlace().getAddress())
                        .draggable(true)
        );
    }
}
