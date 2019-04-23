package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeerRetrofit {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("review")
    @Expose
    private String review;

    @SerializedName("base64img")
    @Expose
    private String base64img;

    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @SerializedName("longitude")
    @Expose
    private Double longitude;

    @SerializedName("date")
    @Expose
    private String date;

    public BeerRetrofit() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBase64img() {
        return base64img;
    }

    public void setBase64img(String base64img) {
        this.base64img = base64img;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BeerRetrofit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", review='" + review + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", date='" + date + '\'' +
                '}';
    }
}
