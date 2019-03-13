package com.example.codemonkeys.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.SolarSystem;
import com.example.codemonkeys.model.Universe;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class UniverseGenerationActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private ScatterChart chart;
    private TextView sizeValue;
    private TextView techLevelValue;
    private TextView governmentValue;
    private TextView resourceValue;
    private TextView policeValue;
    private TextView piratesValue;
    private TextView distanceValue;
    private ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.universe_generation_activity);

        chart = (ScatterChart) findViewById(R.id.scatterChart);
        sizeValue = (TextView) findViewById(R.id.sizeValue);
        techLevelValue = (TextView) findViewById(R.id.techLevelValue);
        governmentValue = (TextView) findViewById(R.id.governmentValue);
        resourceValue = (TextView) findViewById(R.id.resourceValue);
        policeValue = (TextView) findViewById(R.id.policeValue);
        piratesValue = (TextView) findViewById(R.id.piratesValue);
        distanceValue = (TextView) findViewById(R.id.distanceValue);



        Universe u = Universe.getInstance();
        SolarSystem[] solarSystemsArray = u.getUniverse();
        List<Entry> location = new ArrayList<Entry>();
        int x;
        int y;
        for (int i = 0; i < solarSystemsArray.length; i++) {
            x = solarSystemsArray[i].getLocation().getX();
            y = solarSystemsArray[i].getLocation().getY();
            location.add(new Entry(x,y, solarSystemsArray[i])); //can also add images to each entry
        }
        ScatterDataSet dataSet = new ScatterDataSet(location, "test");
        dataSet.setScatterShapeSize(30);
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);

        //Assigning random solar system in universe to player
        viewModel.generatePlayerSolarSystem(solarSystemsArray[new Random().nextInt(15)]);


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
