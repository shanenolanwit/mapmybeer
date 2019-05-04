package com.example.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.api.MapMyBeerAPIClient;
import com.example.api.MapMyBeerAPIInterface;

import com.example.models.BeerCoordListWrapper;
import com.example.models.BeerCoordinates;
import com.example.pubcrawlerv1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StandardMapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private static final String TAG = "SimpleMapFragment";

    private GoogleMap mMap;
    private EditText filterET;
    private Button filterButton;

    public StandardMapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.standard_map_layout, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.simple_map);
        mapFragment.getMapAsync(this);
        filterET = (EditText) view.findViewById(R.id.filterName);
        filterButton = (Button) view.findViewById(R.id.filterByNameButton);
        filterButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mMap.clear();
                updateData();
            }
        });
        return view;
    }

    private void updateData(){
        Log.d(TAG, "updateData: called update data");
        String url = "";
        if(isAdded()){
            url = getResources().getString(R.string.nodemybeer_url);
        }
        Retrofit retrofit = MapMyBeerAPIClient.getRetrofitClient(url);
        MapMyBeerAPIInterface api = retrofit.create(MapMyBeerAPIInterface.class);
        Call call = api.locateBeers("");
        if(filterET != null){
            String beerName = filterET.getText().toString();

            if(!beerName.isEmpty()){
                call = api.locateBeers(beerName);
            }
        }

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "onResponse: Constructor Success");

                BeerCoordListWrapper wrapper = (BeerCoordListWrapper) response.body();
                List<BeerCoordinates> coords = wrapper.getBeers();
                Log.d(TAG, "onResponse: " + response.body());
                for(BeerCoordinates c : coords){
                    LatLng latLng = new LatLng(c.getLatitude(), c.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng)
                            .title(c.getName()).snippet(c.getDate())).showInfoWindow();

                }









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
//            updateData();

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);

        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng waterfordIT = new LatLng(52.24610307207322, -7.137986160814762);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(waterfordIT,10));

        updateData();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(),"You clicked on " + marker.getSnippet(), Toast.LENGTH_LONG);
        return false;
    }

    public EditText getLocationEditText() {
        return filterET;
    }

    public void setLocationEditText(EditText filterET) {
        this.filterET = filterET;
    }


}
