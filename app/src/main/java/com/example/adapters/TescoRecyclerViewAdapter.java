package com.example.adapters;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pubcrawlerv1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class TescoRecyclerViewAdapter extends RecyclerView.Adapter<TescoRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "BeerRecyclerViewAdapter";


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDescriptions = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;

    public TescoRecyclerViewAdapter(Context mContext,
                                    ArrayList<String> mNames,
                                    ArrayList<String> mDescriptions,
                                    ArrayList<String> mImageUrls) {
        this.mContext = mContext;
        this.mNames = mNames;
        this.mDescriptions = mDescriptions;
        this.mImageUrls = mImageUrls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tesco_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: ");
        Picasso.get().load(mImageUrls.get(i)).into((ImageView) viewHolder.image);


        viewHolder.name.setText(mNames.get(i));
        viewHolder.image.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on an image ");
                Toast.makeText(mContext, mDescriptions.get(i), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }




    public void update( ArrayList<String> mNames, ArrayList<String> mDescriptions, ArrayList<String> mBase64images) {
        this.mNames.clear();
        this.mNames.addAll(mNames);
        this.mDescriptions.clear();
        this.mDescriptions.addAll(mDescriptions);
        this.mImageUrls.clear();
        this.mImageUrls.addAll(mBase64images);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
