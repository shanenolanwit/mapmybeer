package com.example.models;

public class TescoInputWrapper {

    TescoQueryStringParams queryStringParameters;

    public TescoInputWrapper() {
    }

    public TescoInputWrapper(TescoQueryStringParams queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    public TescoQueryStringParams getQueryStringParameters() {
        return queryStringParameters;
    }

    public void setQueryStringParameters(TescoQueryStringParams queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }
}
