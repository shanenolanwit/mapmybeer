package com.example.models;

public class TescoQueryStringParams {

    private String limit;
    private String offset;

    public TescoQueryStringParams() {
    }

    public TescoQueryStringParams(String limit, String offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "TescoQueryStringParams{" +
                "limit='" + limit + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
