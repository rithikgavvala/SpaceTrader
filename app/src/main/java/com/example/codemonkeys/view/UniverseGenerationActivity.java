package com.example.codemonkeys.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.SolarSystem;
import com.example.codemonkeys.model.Universe;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniverseGenerationActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.universe_generation_activity);

        ScatterChart chart = (ScatterChart) findViewById(R.id.scatterChart);

//        YourData[] dataObjects = ...;
//
//        List<Entry> entries = new ArrayList<Entry>();
//
//        for (YourData data : dataObjects) {
//
//            // turn your data into Entry objects
//            entries.add(new Entry(data.getValueX(), data.getValueY()));
//        }
//
//        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
//        dataSet.setColor(...);
//        dataSet.setValueTextColor(...); // styling, ...
//        LineData lineData = new LineData(dataSet);
//        chart.setData(lineData);
//        chart.invalidate(); // refresh
        Universe u = Universe.getInstance();
        SolarSystem[] solarSystemsArray = u.getUniverse();
        List<Entry> location = new ArrayList<Entry>();
        int x;
        int y;
        for (int i = 0; i < solarSystemsArray.length; i++) {
            x = solarSystemsArray[i].getLocation().getX();
            y = solarSystemsArray[i].getLocation().getY();
            location.add(new Entry(x,y, solarSystemsArray[i]));
        }
        ScatterDataSet dataSet = new ScatterDataSet(location, "test");
        dataSet.setScatterShapeSize(30);
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);


        ScatterData scatterData = new ScatterData(dataSet);
        chart.setData(scatterData);
        chart.setLogEnabled(true);

        chart.getDescription().setEnabled(false);
        chart.setOnChartValueSelectedListener(this);

        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setMaxHighlightDistance(50f);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        chart.setMaxVisibleValueCount(200);
        chart.setPinchZoom(true);





        chart.invalidate();




    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
