package com.example.api;

import com.example.models.Beer;
import com.example.models.BeerRetrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MapMyBeerAPIInterface {

    @POST("beers/new")
    Call<BeerRetrofit> createBeer(@Body Beer beer);
}
