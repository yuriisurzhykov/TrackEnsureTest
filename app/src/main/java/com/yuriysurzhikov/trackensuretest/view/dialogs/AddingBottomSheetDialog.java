package com.yuriysurzhikov.trackensuretest.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.view.activities.AddingActivity;

public class AddingBottomSheetDialog extends BottomSheetDialogFragment {

    private Context context;
    private View view;
    private NumberPicker fuelTypes;
    private TextInputEditText fuelAmount;

    private String[] fuelTypesList;

    public AddingBottomSheetDialog(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
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
        fuelAmount = view.findViewById(R.id.fuel_amount_picker);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
