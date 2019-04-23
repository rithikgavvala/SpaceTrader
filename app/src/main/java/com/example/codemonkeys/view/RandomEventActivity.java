package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.model.Spaceship;
import com.example.codemonkeys.model.SpaceshipEnum;
import com.example.codemonkeys.model.TradeGood;
import com.example.codemonkeys.model.TradeGoodEnum;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class RandomEventActivity extends AppCompatActivity {

    private final int POLICE = 0;
    private final int TRADE = 1;

    private Button tradeButton;

    private ConfigurationViewModel viewModel;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trade_encounter_activity);

        int clicks = (int) (Math.random() * 20);

        final SpaceshipEnum randShip = SpaceshipEnum.values()[(int)(Math.random() * SpaceshipEnum.values().length)];
        System.out.println(randShip.getName());


        TextView encounterMessage = findViewById(R.id.encounter_message);

        String message = "At " + clicks +" from the planet you want to travel to, you encounter a trader " + randShip.getName();

        encounterMessage.setText(message);

        Button attackButton = findViewById(R.id.attack_button);
        Button ignoreButton = findViewById(R.id.ignore_button);
        Button tradeButton = findViewById(R.id.trade_button);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        Player p = viewModel.getPlayer();
        TradeGood [] tradeGoods = {new TradeGood(TradeGoodEnum.Water), new TradeGood(TradeGoodEnum.Furs), new TradeGood(TradeGoodEnum.Ore), new TradeGood(TradeGoodEnum.Games), new TradeGood(TradeGoodEnum.Firearms),new TradeGood(TradeGoodEnum.Medicine), new TradeGood(TradeGoodEnum.Machines), new TradeGood(TradeGoodEnum.Narcotics), new TradeGood(TradeGoodEnum.Robots)};
        ArrayList<TradeGood> tCargoList = new ArrayList<>();
        int rand = (int)(Math.random() * 4);
        for (int n = 1; n <= rand; n++) {
            int goodNum = (int)(Math.random() * 10);
            tCargoList.add(tradeGoods[goodNum]);
        }
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player p = viewModel.getPlayer();
                Spaceship playerShip = p.getShip();
                int playerAttack = playerShip.getHullStrength();
                int randAttack = randShip.getHullStrength();
                if(Math.random() <= .5){
                    Toast toast = Toast.makeText(getApplicationContext(), "The trader defeated you! You have lost all of your items.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                    toast.show();
                    HashMap<String, Integer> cl = p.getSpaceship().getCargoList();
                    cl.clear();
                    p.getSpaceship().setCargoList(cl);
                    HashMap<String, TradeGood> acl = p.getSpaceship().getActualCargoList();
                    acl.clear();
                    p.getSpaceship().setActualCargoList(acl);
                    Intent returnIntent = new Intent(RandomEventActivity.this, UniverseGenerationActivity.class);
                    startActivity(returnIntent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "The trader has surrendered to you!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                    toast.show();
                    for (int n = 0; n < tCargoList.size(); n++) {
                        p.getSpaceship().addToList(tCargoList.get(n), 1);
                    }
                    Intent returnIntent = new Intent(RandomEventActivity.this, UniverseGenerationActivity.class);
                    startActivity(returnIntent);
                }
            }
        });

        ignoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomEventActivity.this, UniverseGenerationActivity.class);
                startActivity(intent);
            }
        });

        tradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RandomEventActivity.this, MarketActivity.class);
                startActivity(i);
            }
        });




    }
}
