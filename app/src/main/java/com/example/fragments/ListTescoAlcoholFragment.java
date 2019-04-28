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


import com.example.adapters.TescoRecyclerViewAdapter;

import com.example.api.TescoAPIClient;
import com.example.api.TescoAPIInterface;

import com.example.models.TescoListRecord;
import com.example.models.TescoListWrapper;
import com.example.pubcrawlerv1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ListTescoAlcoholFragment extends Fragment {

    private static final String TAG = "ListBeerFragment";

    private TescoRecyclerViewAdapter adapter;

    public ListTescoAlcoholFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<String> mIds = new ArrayList<>();
        ArrayList<String> mNames = new ArrayList<>();
        ArrayList<String> mDescriptions = new ArrayList<>();
        ArrayList<String> mBase64images = new ArrayList<>();

        Retrofit retrofit = TescoAPIClient.getRetrofitClient("https://im33aoagff.execute-api.eu-west-1.amazonaws.com/");
        TescoAPIInterface api = retrofit.create(TescoAPIInterface.class);
        Log.d(TAG, "onCreateView: " + mBase64images.size());
        adapter = new TescoRecyclerViewAdapter(getContext(), mIds, mNames, mDescriptions, mBase64images);

        Call call = api.getAll(2,0);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "ListBeerFragment onResponse: success" );
                TescoListWrapper bl = (TescoListWrapper) response.body();
                Log.d(TAG, "onResponse: " + bl.getProducts());
                addBeers(bl);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "onFailure: Failure");
                Log.d(TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
        View rootView = inflater.inflate(R.layout.tesco_list, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Log.d(TAG, "onViewCreated: called");
    }

    private void addBeers( TescoListWrapper bl ){
        ArrayList<String> mIds = new ArrayList<>();
        ArrayList<String> mNames = new ArrayList<>();
        ArrayList<String> mDescriptions = new ArrayList<>();
        ArrayList<String> mBase64images = new ArrayList<>();
        for (TescoListRecord beer : bl.getProducts()) {
            mIds.add(String.valueOf(beer.getId()));
            mNames.add(beer.getName());
            mDescriptions.add(beer.getDescription().toString());
            mBase64images.add(beer.getImage());
        }
        getAdapter().update(mIds, mNames, mBase64images);
    }

    private void updateData(){
        Retrofit retrofit = TescoAPIClient.getRetrofitClient("https://im33aoagff.execute-api.eu-west-1.amazonaws.com/");
        TescoAPIInterface api = retrofit.create(TescoAPIInterface.class);

        Call call = api.getAll(2,0);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "ListBeerFragment onResponse: success" );
                TescoListWrapper bl = (TescoListWrapper) response.body();
                Log.d(TAG, "onResponse: " + response);
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

    public TescoRecyclerViewAdapter getAdapter() {
        return adapter;
    }
}
