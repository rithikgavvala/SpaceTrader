package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.Model;
import com.example.codemonkeys.model.Player;
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
    private TextView techLevelValue;
    private TextView resourceValue;
    private TextView creditsValue;
    private TextView piratesValue;
    private TextView distanceValue;
    private ConfigurationViewModel viewModel;

    private Button leftArrow;
    private Button rightArrow;

    private final int BUY = 1;
    private final int SELL = 0;
    private final int HOLD = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.universe_generation_activity);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        ScatterChart chart = findViewById(R.id.scatterChart);
        TextView sizeValue = findViewById(R.id.sizeValue);
        techLevelValue = findViewById(R.id.techLevelValue);
        TextView governmentValue = findViewById(R.id.governmentValue);
        resourceValue = findViewById(R.id.resourceValue);
        creditsValue = findViewById(R.id.creditsValue);
        TextView fuelValue = findViewById(R.id.fuelValue);
        distanceValue = findViewById(R.id.distanceValue);
        TextView systemName = findViewById(R.id.systemName);
        Button buyButton = findViewById(R.id.buyButton);
        Button sellButton = findViewById(R.id.sellButton);
        Button holdButton = findViewById(R.id.holdButton);
        Button market = findViewById(R.id.button5);
        Button travelButton = findViewById(R.id.travelButton);



        Universe u = Universe.getInstance();


        List<SolarSystem> solarSystemsArray = u.getPlanets();

        List<Entry> location = new ArrayList<Entry>();
        float x;
        float y;
//        for (int i = 0; i < solarSystemsArray.size(); i++) {
//            //Log.d("Solar System", solarSystemsArray[i].getSystemName());
//            x = solarSystemsArray.get(i).getLocation().getX();
//            y = solarSystemsArray.get(i).getLocation().getY();
//            location.add(new Entry(x,y, solarSystemsArray.get(i))); //can also add images to each entry
//        }
        ScatterDataSet dataSet = new ScatterDataSet(location, "test");
        dataSet.setScatterShapeSize(30);
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);

        //Assigning random solar system in universe to player
        SolarSystem s = viewModel.getPlayer().getSystem();
        Player player = viewModel.getPlayer();
        String fuelLevel = "" + player.getFuelLevel();
        fuelValue.setText(fuelLevel);
        String playerCredits = "" + player.getMoney();
        creditsValue.setText(playerCredits);
        Log.i("playerDebug", viewModel.getPlayer().toString());
        systemName.setText(viewModel.getPlayer().getSystem().getSystemName());
        techLevelValue.setText(s.getTechLevel().toString());
        resourceValue.setText(s.getResources().toString());
        ScatterData scatterData = new ScatterData(dataSet);
        chart.setData(scatterData);
        chart.setLogEnabled(true);



        chart.getDescription().setEnabled(false);
        chart.setOnChartValueSelectedListener(this);

        chart.setDrawGridBackground(false);
        chart.setDragEnabled(true);
        chart.setTouchEnabled(true);
        chart.setMaxHighlightDistance(50f);
        chart.setHighlightPerTapEnabled(true);


        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        chart.setMaxVisibleValueCount(200);
        chart.setPinchZoom(true);


        chart.invalidate();

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transactionIntent = new Intent(UniverseGenerationActivity.this, MarketActivity.class);
                transactionIntent.putExtra("TRANSACTION", SELL);
                startActivity(transactionIntent);

            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transactionIntent = new Intent(UniverseGenerationActivity.this, MarketActivity.class);
                transactionIntent.putExtra("TRANSACTION", BUY);
                startActivity(transactionIntent);
            }
        });
        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transactionIntent = new Intent(UniverseGenerationActivity.this, MarketActivity.class);
                transactionIntent.putExtra("TRANSACTION", HOLD);
                startActivity(transactionIntent);
            }
        });

        travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent travelIntent = new Intent(UniverseGenerationActivity.this, TravelActivity.class);

                startActivity(travelIntent);
            }
        });
        SolarSystem pickPlanet = (SolarSystem) getIntent().getSerializableExtra("PLANET");
        Player p = viewModel.getPlayer();
        if(pickPlanet != null){

            p.setSystem(pickPlanet);
            systemName.setText(pickPlanet.getSystemName());
            resourceValue.setText(pickPlanet.getResources().toString());
            distanceValue.setText(pickPlanet.getLocation().toString());
            String fuel = "" + p.getFuelLevel();
            if (p.getFuelLevel() < 0) {
                String zero = "" + 0;
                fuelValue.setText(zero);

            } else {
                fuelValue.setText(fuel);
            }


            Toast toast = Toast.makeText(getApplicationContext(), "Planet Traveled:" + pickPlanet.getSystemName(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
            toast.show();

        }
        if (p.getFuelLevel() < 0) {
            String anotherZero = "0";
            fuelValue.setText(anotherZero);
        }



    }


    @Override
    protected void onResume() {

        super.onResume();
        Model myModel = Model.getInstance();
        myModel.savePlayer();
        myModel.saveUniverse();
        Player player = viewModel.getPlayer();

        String playerCredits = "" + player.getMoney();
        creditsValue.setText(playerCredits);
        Log.i("TRANSACTION", "CREDITS: " + playerCredits);


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
