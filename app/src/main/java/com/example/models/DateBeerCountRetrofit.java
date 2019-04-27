package com.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateBeerCountRetrofit {

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("count")
    @Expose
    public Integer count;

    public DateBeerCountRetrofit() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DateBeerCountRetrofit{" +
                "date='" + date + '\'' +
                ", count=" + count +
                '}';
    }
}
