package com.yuriysurzhikov.trackensuretest.view.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.*;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.model.entities.Location;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.AddingActivityContract;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.AddingBottomSheetContract;

public class AddingBottomSheetDialog extends BottomSheetDialogFragment implements AddingBottomSheetContract.View {

    private final String BOTTOM_SHEET_TAG = "bottom_sheet_dialog";
    private static final String TAG = "AddingBottomSheetDialog";

    private Activity activity;
    private View view;
    private NumberPicker fuelTypes;
    private TextInputEditText fuelAmount;
    private TextInputEditText providerName;
    private TextInputEditText costText;
    private Refueling refueling;
    private Location location;

    private String[] fuelTypesList;

    private AddingActivityContract.Presenter presenter;

    public AddingBottomSheetDialog(Activity activity) {
        this.activity = activity;
    }

    public void setPresenter(AddingActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        refueling = new Refueling();
        Log.d(TAG, "onCreateDialog: " + refueling.toString());
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
        fuelTypes.setOnValueChangedListener((picker, oldVal, newVal) ->  {
            presenter.changeFuelType(fuelTypesList[newVal]);
        });
        fuelAmount = view.findViewById(R.id.fuel_amount_picker);
        fuelAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0)
                    presenter.changeAmount(Integer.parseInt(s.toString()));
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

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }
}
