package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeerCountRetrofit {

    @SerializedName("total")
    @Expose
    public Integer total;

    @SerializedName("data")
    @Expose
    public List<DateBeerCountRetrofit> data;

    public BeerCountRetrofit() {
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<DateBeerCountRetrofit> getData() {
        return data;
    }

    public void setData(List<DateBeerCountRetrofit> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BeerCountRetrofit{" +
                "total=" + total +
                ", data=" + data +
                '}';
    }
}
