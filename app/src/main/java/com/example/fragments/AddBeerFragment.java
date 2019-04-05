package com.example.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogs.ChangeSensitiveDatePickerDialog;
import com.example.models.Beer;
import com.example.pubcrawlerv1.MainActivity;
import com.example.pubcrawlerv1.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;


public class AddBeerFragment extends Fragment {

    private static final String TAG = "AddBeerFragment";

    private TextView feedmebeerTitleTV;
    private Button beerPicButton;
    private ImageView beerPreview;
    private EditText beerNameET;
    private EditText beerReviewET;
    private EditText beerDateET;
    private EditText beerLocationET;
    private Button addBeerButton;

    private ChangeSensitiveDatePickerDialog beerDatePickerDialog;

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
        beerLocationET = (EditText) view.findViewById(R.id.beerLocation);
        beerLocationET.setGravity(Gravity.CENTER);
        addBeerButton = (Button) view.findViewById(R.id.addBeerButton);

        beerPicButton = (Button) view.findViewById(R.id.beerpic);
        beerPicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        beerPreview = (ImageView) view.findViewById(R.id.beerPreview);

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
                beerDatePickerDialog = new ChangeSensitiveDatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ACTIVITY RESULT " + requestCode + " " + resultCode + " " + data.toString());
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Log.d("CameraDemo", "Pic saved " + data.getExtras());
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            beerPreview.setImageBitmap(imageBitmap);
        }
    }

    private void clearForm(){
       beerPreview.setImageDrawable(null);
       beerNameET.getText().clear();
       beerReviewET.getText().clear();
       beerDateET.getText().clear();
       beerLocationET.getText().clear();
       insertNestedFragment();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment childFragment = new SimpleMapFragment(beerLocationET);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.beer_map, childFragment).commit();
    }



}
