package com.example.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pubcrawlerv1.MainActivity;
import com.example.pubcrawlerv1.R;

public class WelcomeFragment extends Fragment {
    private static final String TAG = "WelcomeFragment";

    private Button welcomeButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.welcome_layout, container, false);
       welcomeButton = (Button) view.findViewById(R.id.welcomeButton);
       welcomeButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d(TAG, "onClick: clicked the button on the welcome screen");
               Toast.makeText(getActivity(), "Going to about page", Toast.LENGTH_SHORT).show();

               ((MainActivity)getActivity()).setViewPager(1);
           }
       });
       return view;
    }
}
