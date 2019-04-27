package com.example.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.MapMyBeerAPIClient;
import com.example.api.MapMyBeerAPIInterface;

import com.example.dialogs.ChangeSensitiveDatePickerDialog;
import com.example.models.BeerCountFilter;
import com.example.models.BeerCountRetrofit;

import com.example.models.DateBeerCountRetrofit;
import com.example.pubcrawlerv1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BeerCountFragment extends Fragment {

    private static final String TAG = "BeerCountFragment";

    private TextView beerCountTitleTV;
    private LineChartView lineChartView;
    private EditText fromDateET;
    private EditText toDateET;
    private ChangeSensitiveDatePickerDialog fromDatePickerDialog;
    private ChangeSensitiveDatePickerDialog toDatePickerDialog;
    private Button filterButton;

    private void updateData(){
        Log.d(TAG, "updateData: called update data");
        Retrofit retrofit = MapMyBeerAPIClient.getRetrofitClient();
        MapMyBeerAPIInterface api = retrofit.create(MapMyBeerAPIInterface.class);
        Call call = api.countBeers();
        if(fromDateET != null && toDateET != null){
            String fromDate = fromDateET.getText().toString();
            String toDate = toDateET.getText().toString();

            if(fromDate != null && toDate != null){
                try{
                    fromDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate));
                    toDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate));
                    call = api.countBeers(new BeerCountFilter(fromDate,toDate));
                } catch(Exception e){
                    Toast.makeText(getContext(), "Error parsing to and from dates", Toast.LENGTH_LONG);
                }

            }
        }

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "onResponse: Constructor Success");
                Log.d(TAG, "onResponse: " + response);
                BeerCountRetrofit beerCountRF = (BeerCountRetrofit) response.body();
                List<DateBeerCountRetrofit> dateCounts = beerCountRF.getData();
                Log.d(TAG, "onResponse: " + response.body());
                Log.d(TAG, "onResponse: " + beerCountRF);
                beerCountTitleTV.setText(beerCountRF.getTotal() + " Beers");

                List yAxisValues = new ArrayList();
                List axisValues = new ArrayList();
                Line line = new Line(yAxisValues);
                int max =0;
                for(int i = 0; i < dateCounts.size(); i++){
                    Log.d(TAG, "onResponse: ");
                    DateBeerCountRetrofit dc = dateCounts.get(i);
                    max = (dc.getCount() > max) ? dc.getCount() : max;
                    axisValues.add(i, new AxisValue(i).setLabel(dc.getDate()));
                    yAxisValues.add(new PointValue(i, dc.getCount()));
                }

                List lines = new ArrayList();
                lines.add(line);
                LineChartData data = new LineChartData();
                data.setLines(lines);

                Axis axis = new Axis();
                axis.setValues(axisValues);
                data.setAxisXBottom(axis);
                Axis yAxis = new Axis();
                data.setAxisYLeft(yAxis);
                yAxis.setName("Sales in millions");

                lineChartView.setLineChartData(data);

                //do this after you had set chart data to make it scrollable
                // https://github.com/lecho/hellocharts-android/issues/29
                Viewport v = new Viewport(lineChartView.getMaximumViewport());

                v.left = 0;
                v.right = dateCounts.size()+1;
                v.top = max + 1;
                lineChartView.setMaximumViewport(v);
                lineChartView.setCurrentViewport(v);
                lineChartView.setViewportCalculationEnabled(false);



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
            updateData();

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beer_count_layout, container, false);
        beerCountTitleTV = (TextView) view.findViewById(R.id.beerCountTitle);
        lineChartView =  (LineChartView) view.findViewById(R.id.chart);
        fromDateET = (EditText) view.findViewById(R.id.fromDate);
        toDateET = (EditText) view.findViewById(R.id.toDate);
        fromDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                fromDatePickerDialog = new ChangeSensitiveDatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        fromDateET.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }

                }, mYear, mMonth, mDay, fromDateET);
                fromDatePickerDialog.show();
            }
        });
        toDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                toDatePickerDialog = new ChangeSensitiveDatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        toDateET.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }

                }, mYear, mMonth, mDay, toDateET);
                toDatePickerDialog.show();
            }
        });
        filterButton = (Button) view.findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked filter");
                updateData();
            }
        });
        updateData();
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
       // TODO: Decide what should go in here if anything
    }


}
