package com.example.models;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.fragments.BeerForm;

import java.util.ArrayList;
import java.util.List;

public class BeerValidator {

    private static final String TAG = "BeerValidator";

    private final BeerForm fragment;

    private String message;

    public BeerValidator(BeerForm fragment) {
        this.fragment = fragment;
        this.message = "Something went wrong";
    }

    public boolean validate(){
        String beerName = getForm().getBeerNameET().getText().toString();
        String beerReview = getForm().getBeerReviewET().getText().toString();
        String beerDate = getForm().getBeerDateET().getText().toString();
        String beerLocation = getForm().getBeerLocationET().getText().toString();
        BitmapDrawable drawable = (BitmapDrawable) getForm().getBeerPreview().getDrawable();
        List<String> errors = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(beerName == null || beerName.length() < 4){
            String msg = "Beer name is too short";
            sb.append(msg);
            sb.append(" ");
            errors.add(msg);
        }
        if(beerReview == null || beerReview.isEmpty()){
            String msg = "Beer review is too short";
            sb.append(msg);
            sb.append(" ");
            errors.add(msg);
        }
        if(beerDate == null || !beerDate.matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")){
            Log.d(TAG, "validate: beerdate " + beerDate);
            String msg = "Beer date is invalid";
            sb.append(msg);
            sb.append(" ");
            errors.add(msg);
        }
        if(beerLocation == null || beerLocation.split(",").length !=2){
            String msg = "Beer location is invalid";
            sb.append(msg);
            sb.append(" ");
            errors.add(msg);
        }
        if(drawable == null || drawable.getBitmap() == null){
            String msg = "Beer picture is invalid";
            sb.append(msg);
            sb.append(" ");
            errors.add(msg);
        }
        setMessage(sb.toString());
        return (errors.size() == 0);
    }

    public BeerForm getForm() {
        return fragment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
