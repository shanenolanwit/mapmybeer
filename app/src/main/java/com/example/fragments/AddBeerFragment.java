package com.example.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogs.BeerDatePickerDialog;
import com.example.models.Beer;
import com.example.pubcrawlerv1.MainActivity;
import com.example.pubcrawlerv1.R;

import java.util.Calendar;


public class AddBeerFragment extends Fragment {

    private static final String TAG = "AddBeerFragment";

    private TextView feedmebeerTitleTV;
    private EditText beerNameET;
    private EditText beerReviewET;
    private EditText beerDateET;
    private Button addBeerButton;

    private BeerDatePickerDialog beerDatePickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_beer_layout, container, false);
        feedmebeerTitleTV = (TextView) view.findViewById(R.id.feedmebeerTitle);
        beerNameET = (EditText) view.findViewById(R.id.beerName);
        beerNameET.setGravity(Gravity.CENTER);
        beerReviewET = (EditText) view.findViewById(R.id.beerReview);
        beerReviewET.setGravity(Gravity.CENTER);
        beerDateET = (EditText) view.findViewById(R.id.beerDate);
        beerDateET.setGravity(Gravity.CENTER);
        addBeerButton = (Button) view.findViewById(R.id.addBeerButton);

        addBeerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked add beer");
                Toast.makeText(getActivity(), "Add Beer", Toast.LENGTH_SHORT).show();
                String beerName = beerNameET.getText().toString();
                String beerReview = beerReviewET.getText().toString();
                String beerDate = beerDateET.getText().toString();
                Beer newBeer = new Beer(beerName,beerReview,beerDate);
                Log.d(TAG, "onClick: clicked add beer " + newBeer);
                clearForm();
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        beerDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                beerDatePickerDialog = new BeerDatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        beerDateET.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }

                }, mYear, mMonth, mDay, beerDateET);
                beerDatePickerDialog.show();
            }
        });
        return view;
    }

    private void clearForm(){
       beerNameET.getText().clear();
       beerReviewET.getText().clear();
       beerDateET.getText().clear();
    }



}
