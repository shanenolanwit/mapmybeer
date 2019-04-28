package com.example.models;

import java.util.List;

public class TescoListWrapper {
    public int status;
    public String message;
    public List<TescoListRecord> products;

    public TescoListWrapper(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TescoListRecord> getProducts() {
        return products;
    }

    public void setProducts(List<TescoListRecord> products) {
        this.products = products;
    }

}
