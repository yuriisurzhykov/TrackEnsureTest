package com.yuriysurzhikov.trackensuretest.view.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.EditingActivityContract;

import java.util.Arrays;

public class EditingBottomSheet extends BottomSheetDialogFragment {

    private final String BOTTOM_SHEET_TAG = "bottom_sheet_dialog";
    private static final String TAG = "EditingBottomSheet";

    private Activity activity;
    protected View view;
    private NumberPicker fuelTypes;
    private TextInputEditText fuelAmount;
    private TextInputEditText providerName;
    private TextInputEditText costText;
    protected Refueling refueling;

    private String[] fuelTypesList;
    private EditingActivityContract.Presenter presenter;

    public EditingBottomSheet(Activity activity, Refueling refueling) {
        this.activity = activity;
        this.refueling = refueling;
    }

    public void setPresenter(EditingActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: " + refueling);
        if(savedInstanceState != null)
            refueling = new Gson().fromJson(getArguments().getString("model"), Refueling.class);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        view = View.inflate(getContext(), R.layout.adding_bottom_sheet, null);
        dialog.setContentView(view);
        ((View)view.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        setUpPickers();
    }

    private void setUpPickers() {
        fuelTypesList = getResources().getStringArray(R.array.fuel_types);
        fuelTypes = view.findViewById(R.id.fuel_type_picker);
        fuelTypes.setMinValue(0);
        fuelTypes.setMaxValue(fuelTypesList.length - 1);
        fuelTypes.setWrapSelectorWheel(true);
        fuelTypes.setDisplayedValues(fuelTypesList);
        fuelTypes.setOnValueChangedListener((picker, oldVal, newVal) -> presenter.changeFuelType(fuelTypesList[newVal]));
        fuelAmount = view.findViewById(R.id.fuel_amount_picker);
        fuelAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0)
                    presenter.changeAmount(Float.parseFloat(s.toString()));
                else
                    presenter.changeAmount(0);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        providerName = view.findViewById(R.id.provider_name_field);
        costText = view.findViewById(R.id.cost_field);
        providerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.changeProvider(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        costText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0)
                    presenter.changeCost(Float.parseFloat(s.toString()));
                else
                    presenter.changeCost(0F);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        Log.d(TAG, "setUpPickers: " + refueling);
        fuelTypes.setValue(Arrays.asList(fuelTypesList).indexOf(refueling.getFuelType()));
        fuelAmount.setText(String.valueOf(refueling.getFuelAmount()));
        costText.setText(String.valueOf(refueling.getCost()));
        providerName.setText(refueling.getProvider());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("model", new Gson().toJson(presenter.getModel()));
    }
}
