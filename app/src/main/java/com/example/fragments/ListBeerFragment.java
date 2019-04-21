package com.example.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.BeerRecyclerViewAdapter;
import com.example.api.MapMyBeerAPIClient;
import com.example.api.MapMyBeerAPIInterface;
import com.example.models.BeerListRetrofit;
import com.example.models.BeerRetrofit;
import com.example.pubcrawlerv1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ListBeerFragment extends Fragment {

    private static final String TAG = "ListBeerFragment";

    private BeerRecyclerViewAdapter adapter;

    public ListBeerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<String> mNames = new ArrayList<>();
        ArrayList<String> mBase64images = new ArrayList<>();

        Retrofit retrofit = MapMyBeerAPIClient.getRetrofitClient();
        MapMyBeerAPIInterface api = retrofit.create(MapMyBeerAPIInterface.class);
        Log.d(TAG, "onCreateView: " + mBase64images.size());
        adapter = new BeerRecyclerViewAdapter(getContext(), mNames, mBase64images);

        Call call = api.getBeers();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "ListBeerFragment onResponse: success" );
                BeerListRetrofit bl = (BeerListRetrofit) response.body();
                Log.d(TAG, "onResponse: " + bl.getBeers());
                ArrayList<String> mNames = new ArrayList<>();
                ArrayList<String> mBase64images = new ArrayList<>();
                for (BeerRetrofit beer : bl.getBeers()) {
                    mNames.add(beer.getName());
                    mBase64images.add(beer.getBase64img());
                }
                getAdapter().update(mNames, mBase64images);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "onFailure: Failure");
                Log.d(TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
        View rootView = inflater.inflate(R.layout.beer_list, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Log.d(TAG, "onViewCreated: called");
    }

    private void updateData(){
        Retrofit retrofit = MapMyBeerAPIClient.getRetrofitClient();
        MapMyBeerAPIInterface api = retrofit.create(MapMyBeerAPIInterface.class);

        Call call = api.getBeers();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "ListBeerFragment onResponse: success" );
                BeerListRetrofit bl = (BeerListRetrofit) response.body();
                Log.d(TAG, "onResponse: " + bl.getBeers());
                ArrayList<String> mNames = new ArrayList<>();
                ArrayList<String> mBase64images = new ArrayList<>();
                for (BeerRetrofit beer : bl.getBeers()) {
                    mNames.add(beer.getName());
                    mBase64images.add(beer.getBase64img());
                }
                getAdapter().update(mNames, mBase64images);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "onFailure: Failure");
                Log.d(TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    // https://stackoverflow.com/questions/41655797/refresh-fragment-when-change-between-tabs
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d(TAG, "setUserVisibleHint: refresssssssh");
            updateData();
        }
    }

    public BeerRecyclerViewAdapter getAdapter() {
        return adapter;
    }
}
