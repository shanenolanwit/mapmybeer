package com.example.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.api.MapMyBeerAPIClient;
import com.example.api.MapMyBeerAPIInterface;

import com.example.models.BeerCountRetrofit;

import com.example.pubcrawlerv1.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BeerCountFragment extends Fragment {

    private static final String TAG = "BeerCountFragment";

    private TextView beerCountTitleTV;

    private void updateData(){
        Log.d(TAG, "updateData: called update data");
        Retrofit retrofit = MapMyBeerAPIClient.getRetrofitClient();
        MapMyBeerAPIInterface api = retrofit.create(MapMyBeerAPIInterface.class);
        Call call = api.countBeers();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "onResponse: Constructor Success");
                Log.d(TAG, "onResponse: " + response);
                BeerCountRetrofit beerCountRF = (BeerCountRetrofit) response.body();
//                BeerRetrofit beer = beerList.getBeers().get(0);
                Log.d(TAG, "onResponse: " + response.body());
                Log.d(TAG, "onResponse: " + beerCountRF);
                beerCountTitleTV.setText(beerCountRF.getTotal() + " Beers");


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "onFailure: Failure");
                Log.d(TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d(TAG, "setUserVisibleHint: refresssssssh");
            updateData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beer_count_layout, container, false);
        beerCountTitleTV = (TextView) view.findViewById(R.id.beerCountTitle);
        updateData();
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
       // TODO: Decide what should go in here if anything
    }


}
