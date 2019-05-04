package com.example.api;

import com.example.models.Beer;
import com.example.models.BeerCoordListWrapper;
import com.example.models.BeerCoordinates;
import com.example.models.BeerCountFilter;
import com.example.models.BeerCountRetrofit;
import com.example.models.BeerListRetrofit;
import com.example.models.BeerRetrofit;
import com.example.models.User;
import com.example.models.UserAuthResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MapMyBeerAPIInterface {

    @POST("auth")
    Call<UserAuthResult> auth(@Body User user);

    @POST("beers/new")
    Call<BeerRetrofit> createBeer(@Body Beer beer);

    @POST("beers/update/{beerId}")
    Call<BeerRetrofit> updateBeer(@Body Beer beer, @Path("beerId") String beerId);

    @GET("beers")
    Call<BeerListRetrofit> getBeers(@Query("email") String email);

    @GET("count")
    Call<BeerCountRetrofit> countBeers(@Query("email") String email);

    @POST("count")
    Call<BeerCountRetrofit> countBeers(@Body BeerCountFilter filter, @Query("email") String email);

    @GET("beers/{beerId}")
    Call<BeerListRetrofit> getBeer(@Path("beerId") String beerId);

    @GET("locate/{name}")
    Call<BeerCoordListWrapper> locateBeers(@Path("name") String name);


    @DELETE("beers/delete/{beerId}")
    Call<BeerRetrofit> deleteBeer(@Path("beerId") String beerId);

}
