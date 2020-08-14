package com.yuriysurzhikov.trackensuretest.view.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.EditingActivityContract;

import java.util.Arrays;

public class EditingBottomSheet extends AddingBottomSheetDialog {

    private View view;
    private EditingActivityContract.Presenter presenter;

    public EditingBottomSheet(Activity activity, Refueling refueling) {
        super(activity);
        super.refueling = refueling;
    }

    public void setPresenter(EditingActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState != null)
            refueling = new Gson().fromJson(savedInstanceState.getString("model"), Refueling.class);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        view = View.inflate(getContext(), R.layout.adding_bottom_sheet, null);
        dialog.setContentView(view);
        ((View)view.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        setUpPickers();
    }

    @Override
    protected void setUpPickers() {
        super.setUpPickers();
        fuelTypes.setValue(Arrays.asList(super.fuelTypesList).indexOf(refueling.getFuelType()));
        fuelAmount.setText(String.valueOf(refueling.getFuelAmount()));
        costText.setText(String.valueOf(refueling.getCost()));
        providerName.setText(refueling.getProvider());
    }

    @Override
    public void showErrorMessage() {
        super.showErrorMessage();
    }

    @Override
    public void startLoading() {
        super.startLoading();
    }

    @Override
    public void stopLoading() {
        super.stopLoading();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("model", new Gson().toJson(refueling));
    }
}
