package com.example.dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * DatePickerDialog that updates the specified EditText on change
 */
public class ChangeSensitiveDatePickerDialog extends DatePickerDialog {

    private static final String TAG = "ChangeSensitiveDatePickerDialog";

    private EditText datePickerEditText;

    public ChangeSensitiveDatePickerDialog(@NonNull Context context, @Nullable DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth, EditText datePickerEditText) {
        super(context, listener, year, month, dayOfMonth);
        this.datePickerEditText = datePickerEditText;
    }

    @Override
    public void onDateChanged(@NonNull DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        super.onDateChanged(view, year, monthOfYear, dayOfMonth);
        Log.d(TAG, "onDateChanged: dismissing after change");
        getDatePickerEditText().setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        dismiss();
    }

    public EditText getDatePickerEditText() {
        return datePickerEditText;
    }

    public void setDatePickerEditText(EditText datePickerEditText) {
        this.datePickerEditText = datePickerEditText;
    }
}
