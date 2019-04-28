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
import android.widget.Button;


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
    private Button previousButton;
    private Button nextButton;
    int offset = 0;

    public ListTescoAlcoholFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<String> mNames = new ArrayList<>();
        ArrayList<String> mDescriptions = new ArrayList<>();
        ArrayList<String> mImages = new ArrayList<>();
        String tescoApiUrl = getResources().getString(R.string.tesco_lambda_url);
        Retrofit retrofit = TescoAPIClient.getRetrofitClient(tescoApiUrl);
        TescoAPIInterface api = retrofit.create(TescoAPIInterface.class);
        Log.d(TAG, "onCreateView: " + mImages.size());
        adapter = new TescoRecyclerViewAdapter(getContext(), mNames, mDescriptions, mImages);

        View rootView = inflater.inflate(R.layout.tesco_list, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        previousButton = (Button) rootView.findViewById(R.id.prevButton);
        nextButton = (Button) rootView.findViewById(R.id.nextButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offset > 0){
                    offset = offset-10;
                    updateData();
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    offset = offset+10;
                    updateData();
            }
        });
        updateData();


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Log.d(TAG, "onViewCreated: called");
    }

    private void addBeers( TescoListWrapper wrapper ){
        ArrayList<String> mNames = new ArrayList<>();
        ArrayList<String> mDescriptions = new ArrayList<>();
        ArrayList<String> mImages = new ArrayList<>();
        for (TescoListRecord product : wrapper.getProducts()) {
            mNames.add(product.getName());
            mDescriptions.add(product.getDescription().toString());
            mImages.add(product.getImage());
        }
        getAdapter().update(mNames, mDescriptions, mImages);
    }

    private void updateData(){
        Retrofit retrofit = TescoAPIClient.getRetrofitClient("https://im33aoagff.execute-api.eu-west-1.amazonaws.com/");
        TescoAPIInterface api = retrofit.create(TescoAPIInterface.class);
        int limit = 3;
        Call call = api.getAll(limit,getOffset());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "ListBeerFragment onResponse: success" );
                TescoListWrapper wrapper = (TescoListWrapper) response.body();
                Log.d(TAG, "onResponse: " + response);
                addBeers(wrapper);
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

    public Button getPreviousButton() {
        return previousButton;
    }

    public void setPreviousButton(Button previousButton) {
        this.previousButton = previousButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
