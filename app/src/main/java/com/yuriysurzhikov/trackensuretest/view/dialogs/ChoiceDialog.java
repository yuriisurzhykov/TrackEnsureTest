package com.yuriysurzhikov.trackensuretest.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.yuriysurzhikov.trackensuretest.R;

public class ChoiceDialog extends DialogFragment {

    private DialogListener dialogListener;
    private View view;
    private Context context;
    private LinearLayout delete;
    private LinearLayout edit;

    public ChoiceDialog(Context context, DialogListener dialogListener) {
        this.dialogListener = dialogListener;
        this.context = context;
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        view = View.inflate(getContext(), R.layout.note_action_layout, null);
        dialog.setContentView(view);
        delete = view.findViewById(R.id.delete_linear_layout);
        edit = view.findViewById(R.id.edit_linear_layout);
        delete.setOnClickListener(v -> {
            dialogListener.onDeleteClickListener(v);
            dismiss();
        });
        edit.setOnClickListener(v -> {
            dialogListener.onEditClickListener(v);
            dismiss();
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public interface DialogListener {
        void onDeleteClickListener(View view);
        void onEditClickListener(View view);
    }
}
