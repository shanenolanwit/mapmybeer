package com.example.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.MapMyBeerAPIClient;
import com.example.api.MapMyBeerAPIInterface;
import com.example.dialogs.ChangeSensitiveDatePickerDialog;
import com.example.models.Beer;
import com.example.models.BeerCoordinates;
import com.example.models.BeerListRetrofit;
import com.example.models.BeerRetrofit;
import com.example.models.BeerValidator;
import com.example.pubcrawlerv1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class EditBeerFragment extends Fragment implements BeerForm {

    private static final String TAG = "AddBeerFragment";

    private TextView feedmebeerTitleTV;
    private Button beerPicButton;
    private ImageView beerPreview;
    private EditText beerNameET;
    private EditText beerReviewET;
    private EditText beerDateET;
    private EditText beerLocationET;
    private EditText beerIdET;
    private Button editBeerButton;
    private Button deleteBeerButton;
    private String beerId;


    private ChangeSensitiveDatePickerDialog beerDatePickerDialog;

    private void updateData(){
        Retrofit retrofit = MapMyBeerAPIClient.getRetrofitClient();
        MapMyBeerAPIInterface api = retrofit.create(MapMyBeerAPIInterface.class);
        Call call = api.getBeer(getBeerId());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "onResponse: Constructor Success");
                Log.d(TAG, "onResponse: " + response);
                BeerListRetrofit beerList = (BeerListRetrofit) response.body();
                BeerRetrofit beer = beerList.getBeers().get(0);
                Log.d(TAG, "onResponse: " + response.body());
                Log.d(TAG, "onResponse: " + beer);
                feedmebeerTitleTV.setText(beer.getName());
                beerIdET.setText(String.valueOf(beer.getId()));
                beerNameET.setText(beer.getName());
                beerReviewET.setText(beer.getReview());
                beerDateET.setText(beer.getDate());
                beerLocationET.setText(beer.getLatitude() + "," + beer.getLongitude());

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "onFailure: Failure");
                Log.d(TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_beer_layout, container, false);
        feedmebeerTitleTV = (TextView) view.findViewById(R.id.feedmebeerTitle);

        beerNameET = (EditText) view.findViewById(R.id.beerName);
        beerNameET.setGravity(Gravity.CENTER);
        beerReviewET = (EditText) view.findViewById(R.id.beerReview);
        beerReviewET.setGravity(Gravity.CENTER);
        beerDateET = (EditText) view.findViewById(R.id.beerDate);
        beerDateET.setGravity(Gravity.CENTER);
        beerLocationET = (EditText) view.findViewById(R.id.beerLocation);
        beerLocationET.setGravity(Gravity.CENTER);
        beerIdET = (EditText) view.findViewById(R.id.beerId);
        editBeerButton = (Button) view.findViewById(R.id.editBeerButton);
        deleteBeerButton = (Button) view.findViewById(R.id.editBeerButton);

        beerPicButton = (Button) view.findViewById(R.id.beerpic);
        beerPicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        beerPreview = (ImageView) view.findViewById(R.id.beerPreview);
        final EditBeerFragment f = this;
        editBeerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked add beer");
                Toast.makeText(getActivity(), "Add Beer", Toast.LENGTH_SHORT).show();
                String beerName = beerNameET.getText().toString();
                String beerReview = beerReviewET.getText().toString();
                String beerDate = beerDateET.getText().toString();
                String beerId = beerIdET.getText().toString();
                try {
                    Log.d(TAG, "onClick: beerdate before format " + beerDate);
                    Date sourceDate = new SimpleDateFormat("dd/MM/yyyy").parse(beerDate);
                    beerDate = new SimpleDateFormat("yyyy-MM-dd").format(sourceDate);
                    Log.d(TAG, "onClick: beerdate after format " + beerDate);
                } catch (NullPointerException | ParseException e) {
                    e.printStackTrace();
                }
                String beerLocation = beerLocationET.getText().toString();
                BitmapDrawable drawable = (BitmapDrawable) beerPreview.getDrawable();
                BeerValidator validator = new BeerValidator(f);
                if(validator.validate()){
                    Bitmap img = drawable.getBitmap();
                    String[] latLng = beerLocation.split(",");
                    BeerCoordinates beerCoordinates = new BeerCoordinates(latLng[0],latLng[1]);
                    Beer newBeer = new Beer(beerName,beerReview,img,beerCoordinates,beerDate);
                    newBeer.setBeerId(beerId);
                    Log.d(TAG, "onClick: clicked update beer " + beerName);

                    Toast.makeText(getContext(),"Updated Beer", Toast.LENGTH_SHORT).show();

                    Retrofit retrofit = MapMyBeerAPIClient.getRetrofitClient();
                    MapMyBeerAPIInterface api = retrofit.create(MapMyBeerAPIInterface.class);
                    Call call = api.updateBeer(newBeer, beerId);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.d(TAG, "onResponse: Success");
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d(TAG, "onFailure: Failure");
                            Log.d(TAG, "onFailure: " + t.getMessage());
                            t.printStackTrace();
                        }
                    });
                    clearForm();
//                    ((MainActivity)getActivity()).setViewPager(1);
                }else{
                    Log.d(TAG, "onClick: Error Creating beer");
                    Toast.makeText(getContext(),validator.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        beerDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                beerDatePickerDialog = new ChangeSensitiveDatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        beerDateET.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }

                }, mYear, mMonth, mDay, beerDateET);
                beerDatePickerDialog.show();
            }
        });
        updateData();

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ACTIVITY RESULT " + requestCode + " " + resultCode + " " + data.toString());
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Log.d("CameraDemo", "Pic saved " + data.getExtras());
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            beerPreview.setImageBitmap(imageBitmap);
        }
    }

    private void clearForm(){
       beerPreview.setImageDrawable(null);
       beerNameET.getText().clear();
       beerReviewET.getText().clear();
       beerDateET.getText().clear();
       beerLocationET.getText().clear();
       insertNestedFragment();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment childFragment = new SimpleMapFragment(beerLocationET);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.beer_map, childFragment).commit();
    }


    public String getBeerId() { return beerId;  }

    public void setBeerId(String beerId) {
        this.beerId = beerId;
    }

    public EditText getBeerIdET() { return beerIdET; }

    public ImageView getBeerPreview() {
        return beerPreview;
    }

    public void setBeerPreview(ImageView beerPreview) {
        this.beerPreview = beerPreview;
    }

    public EditText getBeerNameET() {
        return beerNameET;
    }

    public void setBeerNameET(EditText beerNameET) {
        this.beerNameET = beerNameET;
    }

    public EditText getBeerReviewET() {
        return beerReviewET;
    }

    public void setBeerReviewET(EditText beerReviewET) {
        this.beerReviewET = beerReviewET;
    }

    public EditText getBeerDateET() {
        return beerDateET;
    }

    public void setBeerDateET(EditText beerDateET) {
        this.beerDateET = beerDateET;
    }

    public EditText getBeerLocationET() {
        return beerLocationET;
    }

    public void setBeerLocationET(EditText beerLocationET) {
        this.beerLocationET = beerLocationET;
    }
}
