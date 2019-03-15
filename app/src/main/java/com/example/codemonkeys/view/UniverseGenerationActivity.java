package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.SolarSystem;
import com.example.codemonkeys.model.Universe;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class UniverseGenerationActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private ScatterChart chart;
    private TextView sizeValue;
    private TextView techLevelValue;
    private TextView governmentValue;
    private TextView resourceValue;
    private TextView policeValue;
    private TextView piratesValue;
    private TextView distanceValue;
    private TextView systemName;
    private ConfigurationViewModel viewModel;
    private Button market;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.universe_generation_activity);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        chart = (ScatterChart) findViewById(R.id.scatterChart);
        sizeValue = (TextView) findViewById(R.id.sizeValue);
        techLevelValue = (TextView) findViewById(R.id.techLevelValue);
        governmentValue = (TextView) findViewById(R.id.governmentValue);
        resourceValue = (TextView) findViewById(R.id.resourceValue);
        policeValue = (TextView) findViewById(R.id.policeValue);
        piratesValue = (TextView) findViewById(R.id.piratesValue);
        distanceValue = (TextView) findViewById(R.id.distanceValue);
        systemName = (TextView) findViewById(R.id.systemName);
        market = findViewById(R.id.button5);

        Universe u = Universe.getInstance();
        SolarSystem[] solarSystemsArray = u.getUniverse();
        List<Entry> location = new ArrayList<Entry>();
        int x;
        int y;
        for (int i = 0; i < solarSystemsArray.length; i++) {
            //Log.d("Solar System", solarSystemsArray[i].getSystemName());
            Log.i("universe", "" + solarSystemsArray[i].getTechLevel());
            x = solarSystemsArray[i].getLocation().getX();
            y = solarSystemsArray[i].getLocation().getY();
            location.add(new Entry(x,y, solarSystemsArray[i])); //can also add images to each entry
        }
        ScatterDataSet dataSet = new ScatterDataSet(location, "test");
        dataSet.setScatterShapeSize(30);
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);

        //Assigning random solar system in universe to player
        viewModel.generatePlayerSolarSystem(solarSystemsArray[(int)(Math.random() * 15)]);


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


//        chart.invalidate();

        market.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(UniverseGenerationActivity.this, MarketActivity.class));
            }
        });
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("universe", "hello GOT IT");
        SolarSystem solarSystem = (SolarSystem) e.getData();
        //sizeValue.setText(solarSystem.getSize());
        techLevelValue.setText(solarSystem.getTechLevel().toString());
        //governmentValue.setText(solarSystem.get);
        resourceValue.setText(solarSystem.getResources().toString());
        distanceValue.setText(solarSystem.getLocation().toString());


    }
    @Override
    public void onNothingSelected() {

    }
}
