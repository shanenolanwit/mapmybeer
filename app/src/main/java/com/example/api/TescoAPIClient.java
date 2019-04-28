package com.example.api;

import com.example.pubcrawlerv1.MainActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TescoAPIClient {


        public static Retrofit retrofit;

        public static Retrofit getRetrofitClient(String url) {
            //If condition to ensure we don't create multiple retrofit instances in a single application
            if (retrofit == null) {
                //Defining the Retrofit using Builder

                retrofit = new Retrofit.Builder()
                        .baseUrl(url) //This is the only mandatory call on Builder object.
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }

}
