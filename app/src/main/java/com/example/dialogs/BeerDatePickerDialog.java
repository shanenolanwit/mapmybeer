package com.example.dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

public class BeerDatePickerDialog extends DatePickerDialog {

    private static final String TAG = "BeerDatePickerDialog";

    private EditText beerDateET;

    public BeerDatePickerDialog(@NonNull Context context, @Nullable DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth, EditText beerDateET) {
        super(context, listener, year, month, dayOfMonth);
        this.beerDateET = beerDateET;
    }



    @Override
    public void onDateChanged(@NonNull DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        super.onDateChanged(view, year, monthOfYear, dayOfMonth);
        Log.d(TAG, "onDateChanged: dismissing after change");
        getBeerDateET().setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        dismiss();
    }

    public EditText getBeerDateET() {
        return beerDateET;
    }

    public void setBeerDateET(EditText beerDateET) {
        this.beerDateET = beerDateET;
    }
}
