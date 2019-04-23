package com.example.fragments;

import android.widget.EditText;
import android.widget.ImageView;

public interface BeerForm {

    public EditText getBeerIdET();
    public EditText getBeerNameET();
    public EditText getBeerReviewET();
    public EditText getBeerDateET();
    public EditText getBeerLocationET();
    public ImageView getBeerPreview();
}
