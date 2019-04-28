package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeerCoordListWrapper {

    @SerializedName("data")
    @Expose
    public List<BeerCoordinates> beers;

    public BeerCoordListWrapper() {
    }

    public List<BeerCoordinates> getBeers() {
        return beers;
    }

    public void setBeers(List<BeerCoordinates> beers) {
        this.beers = beers;
    }
}
