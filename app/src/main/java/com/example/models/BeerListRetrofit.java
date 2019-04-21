package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeerListRetrofit {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("data")
    @Expose
    public List<BeerRetrofit> beers;

    public BeerListRetrofit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BeerRetrofit> getBeers() {
        return beers;
    }

    public void setBeers(List<BeerRetrofit> beers) {
        this.beers = beers;
    }
}
