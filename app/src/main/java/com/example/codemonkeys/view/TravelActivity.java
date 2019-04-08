package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.constraint.solver.widgets.ConstraintWidgetGroup;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.Location;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.model.SolarSystem;
import com.example.codemonkeys.model.Universe;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;
import com.example.codemonkeys.viewmodel.TravelViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TravelActivity extends AppCompatActivity {
    public static final String SOLARSYSTEM_DATA = "SOLARSYSTEM_DATA";

    private TextView travelMessage;
    private TravelAdapter travelAdapter;
    private ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_activity);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.planet_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        travelMessage = findViewById(R.id.travelMessage);
        if(viewModel.getPlayer().getTravelList().size() == 1){
            travelMessage.setVisibility(View.VISIBLE);
        }else{
            travelMessage.setVisibility(View.INVISIBLE);
            travelAdapter = new TravelAdapter();
            recyclerView.setAdapter(travelAdapter);
            travelAdapter.setOnSolarSystemClickListener(new TravelAdapter.OnSolarSystemClickListener() {
                @Override
                public void onSolarSystemClicked(SolarSystem solarSys) {
                    Player p = viewModel.getPlayer();
                    SolarSystem s = solarSys;

                    int distance = p.calcDistance(p.getSystem(), s);
                    int parsecs = distance / 7;
                    p.setFuelLevel(p.getFuelLevel() - parsecs);

                    if(p.getFuelLevel() < parsecs && p.getFuelLevel() < 0){
                        Toast toast = Toast.makeText(getApplicationContext(), "Can't travel not enough fuel", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                        toast.show();
                    }else{
                        ArrayList<Class> activityList = new ArrayList<>();
                        activityList.add(RandomEventActivity.class);
                        activityList.add(UniverseGenerationActivity.class);

                        Random gen = new Random();
                        int num = gen.nextInt(3) + 1;
                        Class activity = null;

                        switch (num){
                            case 1:
                                activity = RandomEventActivity.class;
                                activityList.remove(RandomEventActivity.class);
                                Toast toast = Toast.makeText(getApplicationContext(), "You have encountered a random event.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                                toast.show();

                                break;
                            default:
                                activity = UniverseGenerationActivity.class;
                                activityList.remove(UniverseGenerationActivity.class);
                                break;
                        }
                        Intent intent = new Intent(getApplicationContext(), activity);
                        startActivity(intent);





                            //Intent intent = new Intent(TravelActivity.this, UniverseGenerationActivity.class);
                            //intent.putExtra("PLANET", solarSys);
                            //Log.d("PLANET TRAVELED", solarSys.getSystemName());
                            //startActivityForResult(intent, 1);
                        }



                }
            });
        }



       Log.d("APP", viewModel.getPlayer().getSystem().toString());

    }



}
