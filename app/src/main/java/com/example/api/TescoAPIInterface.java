package com.example.api;

import com.example.models.TescoListWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TescoAPIInterface {

    @GET("dev/alcohols")
    Call<TescoListWrapper> getAll(@Query("limit") int limit, @Query("offset") int offset);

}
