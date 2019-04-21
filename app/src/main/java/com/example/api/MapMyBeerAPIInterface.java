package com.example.api;

import com.example.models.Beer;
import com.example.models.BeerListRetrofit;
import com.example.models.BeerRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface MapMyBeerAPIInterface {

    @POST("beers/new")
    Call<BeerRetrofit> createBeer(@Body Beer beer);

    @GET("beers")
    Call<BeerListRetrofit> getBeers();


}
