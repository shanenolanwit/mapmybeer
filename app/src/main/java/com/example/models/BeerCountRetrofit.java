package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeerCountRetrofit {

    @SerializedName("total")
    @Expose
    public Integer total;

    public BeerCountRetrofit() {
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BeerCountRetrofit{" +
                "total=" + total +
                '}';
    }
}
