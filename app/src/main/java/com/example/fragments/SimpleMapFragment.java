package com.example.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pubcrawlerv1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class SimpleMapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private static final String TAG = "SimpleMapFragment";

    private GoogleMap mMap;
    private EditText locationEditText;

    public SimpleMapFragment() {
    }

    public SimpleMapFragment(EditText locationEditText){
        this.locationEditText = locationEditText;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.simple_map_layout, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.simple_map);
        mapFragment.getMapAsync(this);
        return view;
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
        LatLng waterfordIT = new LatLng(52.2462, -7.1202);

        mMap.addMarker(new MarkerOptions().position(waterfordIT).title("Waterford IT").snippet("52.2462, -7.1202")).showInfoWindow();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(waterfordIT,17));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                String latLongString = latLng.latitude + "," + latLng.longitude;
                Log.d(TAG, "onMapClick: " + latLng.latitude + "," + latLng.longitude);
                mMap.clear();
                LatLng location = new LatLng(latLng.latitude, latLng.longitude);
                Geocoder geoCoder = new Geocoder(getContext());
                String title = "unknown";
                String snippet = latLongString;
                try {
                    List<Address> matches = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    Address bestMatch = (matches.isEmpty() ? null : matches.get(0));
                    title = bestMatch.getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                mMap.addMarker(new MarkerOptions().position(location).title(title).snippet(snippet)).showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,17));
                getLocationEditText().setText(latLongString);
            }
        });
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(),"You clicked on " + marker.getSnippet(), Toast.LENGTH_LONG);
        return false;
    }

    public EditText getLocationEditText() {
        return locationEditText;
    }

    public void setLocationEditText(EditText locationEditText) {
        this.locationEditText = locationEditText;
    }


}
