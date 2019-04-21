package com.example.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fragments.SimpleMapFragment;
import com.example.pubcrawlerv1.MainActivity;
import com.example.pubcrawlerv1.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BeerRecyclerViewAdapter extends RecyclerView.Adapter<BeerRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "BeerRecyclerViewAdapter";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mBase64images = new ArrayList<>();
    private Context mContext;

    public BeerRecyclerViewAdapter(Context mContext, ArrayList<String> mNames, ArrayList<String> mBase64images) {
        this.mContext = mContext;
        this.mNames = mNames;
        this.mBase64images = mBase64images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.beer_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: ");
        Glide.with(mContext)
                .asBitmap()
                .load(Base64.decode(mBase64images.get(i), Base64.DEFAULT))
                .into(viewHolder.image);


        viewHolder.name.setText(mNames.get(i));
        viewHolder.image.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on an image ");
                Toast.makeText(mContext, mNames.get(i), Toast.LENGTH_SHORT).show();
                Fragment childFragment = new SimpleMapFragment();
                FragmentManager fragmentManager = ((MainActivity)mContext).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.child_fragment_container, childFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }

    public void update(ArrayList<String> mNames, ArrayList<String> mBase64images) {
        this.mNames.clear();
        this.mNames.addAll(mNames);
        this.mBase64images.clear();
        this.mBase64images.addAll(mBase64images);
        this.notifyDataSetChanged();
    }
}
