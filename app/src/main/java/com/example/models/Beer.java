package com.example.models;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Beer implements Serializable {

    public String beerId;
    public String email;
    private String name;
    private String review;
    private Bitmap img;
    private String base64img;
    private BeerCoordinates coordinates;
    private String date; //YYYY-MM-DD matches with MYSQL DATE


    public Beer() {}

    public Beer(String email, String name, String review, Bitmap img, BeerCoordinates coordinates, String date) {
        this.email = email;
        this.name = name;
        this.review = review;
        this.img = img;
        this.coordinates = coordinates;

        this.date = date;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        this.base64img = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }

    public String getBeerId() {
        return beerId;
    }

    public void setBeerId(String beerId) {
        this.beerId = beerId;
    }

    public String getEmail() {  return email;  }

    public void setEmail(String email) { this.email = email; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public BeerCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(BeerCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
