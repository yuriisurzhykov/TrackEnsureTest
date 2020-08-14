package com.yuriysurzhikov.trackensuretest.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.presenter.EditingActivityPresenter;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.EditingActivityContract;
import com.yuriysurzhikov.trackensuretest.view.dialogs.EditingBottomSheet;

import org.jetbrains.annotations.NotNull;

public class EditingActivity extends AppCompatActivity implements EditingActivityContract.View, OnMapReadyCallback {

    private static final String TAG = "EditingActivity";
    private static final String ARG_TAB = "ARG_TAB";
    private static final String SHEET_TAG = "SHEET_TAG";

    private EditingActivityContract.Presenter presenter;
    private Refueling refueling;
    private GoogleMap googleMap;
    private SupportMapFragment mapFragment;
    private EditingBottomSheet dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_layout);
        init(getIntent().getExtras());
    }

    private void init(Bundle bundle) {
        Log.d(TAG, "init: " + bundle);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter = new EditingActivityPresenter(this, this);
        if (bundle != null) {
            refueling = new Gson().fromJson(bundle.getString(ARG_TAB), Refueling.class);
            presenter.setModel(refueling);
        }

    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void openBottomSheet() {
        if(dialog == null) {
            dialog = new EditingBottomSheet(this, refueling);
            dialog.setPresenter(presenter);
        }
        dialog.show(getSupportFragmentManager(), SHEET_TAG);
    }

    @Override
    public void closeBottomSheet() {
        dialog.dismiss();
    }

    @Override
    public void createMarker(@NotNull LatLng latLng) {
        googleMap.clear();
        MarkerOptions marker = new MarkerOptions()
                .position(latLng)
                .draggable(true)
                .title(refueling.getLocation().getPlaceName());
        googleMap.addMarker(marker);
    }

    @Override
    public void showMessage(@NotNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        //this.googleMap.setMyLocationEnabled(true);
        this.googleMap.setOnMapLongClickListener(presenter);
        this.googleMap.setOnMarkerDragListener(presenter);
        presenter.onMapReady(googleMap);
    }

    public void finish(View view) {
        finish();
    }
}
