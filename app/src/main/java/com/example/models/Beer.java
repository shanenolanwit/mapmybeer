package com.example.models;

import java.util.Date;

public class Beer {

    private String beerName;
    private String beerReview;
    private Date beerDate;

    public Beer() {}

    public Beer(String beerName, String beerReview, Date beerDate) {
        this.beerName = beerName;
        this.beerReview = beerReview;
        this.beerDate = beerDate;
    }

    public Beer(String beerName, String beerReview, String beerDateAsString) {
        this.beerName = beerName;
        this.beerReview = beerReview;
        this.beerDate = new Date();
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBeerReview() {
        return beerReview;
    }

    public void setBeerReview(String beerReview) {
        this.beerReview = beerReview;
    }

    public Date getBeerDate() {
        return beerDate;
    }

    public String getBeerDateAsString() {
        return beerDate.toString();
    }

    public void setBeerDate(Date beerDate) {
        this.beerDate = beerDate;
    }

    public void setBeerDate(String beerDateAsString) {
        this.beerDate = new Date();
    }

    @Override
    public String toString() {
        return "Beer{" +
                "beerName='" + beerName + '\'' +
                ", beerReview='" + beerReview + '\'' +
                ", beerDate=" + getBeerDateAsString() +
                '}';
    }
}
