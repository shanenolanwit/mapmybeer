package com.example.models;

public class BeerCoordinates {

    private double latitude;
    private double longitude;
    private String address;

    public BeerCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BeerCoordinates(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public BeerCoordinates(String address) {
        this.address = address;
        reverseGeoCode(address);
    }

    private void geoCode(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = "Alaska";
    }

    private void reverseGeoCode(String addr){
        setLatitude(0);
        setLongitude(0);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
