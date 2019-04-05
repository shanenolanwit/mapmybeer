package com.example.models;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Beer implements Serializable {

    public String _id;
    private String name;
    private String review;
    private Bitmap img;
    private String base64img;
    private BeerCoordinates coordinates;
    private String date;

    public Beer() {}

    public Beer(String name, String review, Bitmap img, BeerCoordinates coordinates, String date) {
        this.name = name;
        this.review = review;
        this.img = img;
        this.coordinates = coordinates;
        this.date = date;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        this.base64img = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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
