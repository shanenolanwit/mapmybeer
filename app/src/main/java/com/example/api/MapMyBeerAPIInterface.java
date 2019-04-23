package com.example.api;

import com.example.models.Beer;
import com.example.models.BeerListRetrofit;
import com.example.models.BeerRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MapMyBeerAPIInterface {

    @POST("beers/new")
    Call<BeerRetrofit> createBeer(@Body Beer beer);

    @POST("beers/update/{beerId}")
    Call<BeerRetrofit> updateBeer(@Body Beer beer, @Path("beerId") String beerId);

    @GET("beers")
    Call<BeerListRetrofit> getBeers();

    @GET("beers/{beerId}")
    Call<BeerListRetrofit> getBeer(@Path("beerId") String beerId);

    @DELETE("beers/delete/{beerId}")
    Call<BeerRetrofit> deleteBeer(@Path("beerId") String beerId);

}
