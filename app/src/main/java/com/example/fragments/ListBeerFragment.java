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
import com.example.pubcrawlerv1.R;

import java.util.ArrayList;


public class ListBeerFragment extends Fragment {

    private static final String TAG = "ListBeerFragment";

    public ListBeerFragment() {
    }



//    public View doSomething(View view) {
//
//
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(layoutManager);
//        ArrayList<String> mNames = new ArrayList<>();
//        ArrayList<String> mBase64images = new ArrayList<>();
//        mNames.add("image 1");
//        mNames.add("image 2");
//        mNames.add("image 3");
//        mNames.add("image 4");
//        mBase64images.add("https://ui.sportsbetting.ag/nts/images/paige-vanzant-eyes-return.jpg");
//        mBase64images.add("https://pbs.twimg.com/profile_images/1025970166260064256/XWnsJ8PZ_400x400.jpg");
//        mBase64images.add("https://www.lowkickmma.com/wp-content/uploads/2018/09/9135772_web1_web-pvz-082616.jpg");
//        mBase64images.add("https://www.samaa.tv/wp-content/uploads/2018/11/Paige-Vanzant-1-640x356.jpg");
//        BeerRecyclerViewAdapter adapter = new BeerRecyclerViewAdapter(getContext(),null,null);
//        recyclerView.setAdapter(adapter);
//        return recyclerView;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.beer_list, container, false);

            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);


            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

            ArrayList<String> mNames = new ArrayList<>();
            ArrayList<String> mBase64images = new ArrayList<>();
            mNames.add("image 1");
            mNames.add("image 2");
            mNames.add("image 3");
            mNames.add("image 4");
            mNames.add("image 1");
            mNames.add("image 2");
            mNames.add("image 3");
            mBase64images.add("https://ui.sportsbetting.ag/nts/images/paige-vanzant-eyes-return.jpg");
            mBase64images.add("https://pbs.twimg.com/profile_images/1025970166260064256/XWnsJ8PZ_400x400.jpg");
            mBase64images.add("https://www.lowkickmma.com/wp-content/uploads/2018/09/9135772_web1_web-pvz-082616.jpg");
            mBase64images.add("https://www.samaa.tv/wp-content/uploads/2018/11/Paige-Vanzant-1-640x356.jpg");
            mBase64images.add("https://ui.sportsbetting.ag/nts/images/paige-vanzant-eyes-return.jpg");
            mBase64images.add("https://pbs.twimg.com/profile_images/1025970166260064256/XWnsJ8PZ_400x400.jpg");
            mBase64images.add("https://www.lowkickmma.com/wp-content/uploads/2018/09/9135772_web1_web-pvz-082616.jpg");
            BeerRecyclerViewAdapter adapter = new BeerRecyclerViewAdapter(getContext(),mNames,mBase64images);
            recyclerView.setAdapter(adapter);

            recyclerView.setItemAnimator(new DefaultItemAnimator());

            return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Log.d(TAG, "onViewCreated: called");
    }




}
