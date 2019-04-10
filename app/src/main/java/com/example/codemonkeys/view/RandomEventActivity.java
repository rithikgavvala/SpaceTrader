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
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;


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

        final Spaceship randShip = Spaceship.values()[(int)(Math.random() * Spaceship.values().length)];
        System.out.println(randShip.getName());


        TextView encounterMessage = findViewById(R.id.encounter_message);

        String message = "At " + clicks +" from the planet you want to travel to, you encounter a trader " + randShip.getName();

        encounterMessage.setText(message);

        Button attackButton = findViewById(R.id.attack_button);
        Button ignoreButton = findViewById(R.id.ignore_button);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        Player p = viewModel.getPlayer();

        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player p = viewModel.getPlayer();
                Spaceship playerShip = p.getShip();
                int playerAttack = playerShip.getHullStrength();
                int randAttack = randShip.getHullStrength();
                if(playerAttack * Math.random() * 0 > randAttack){
                    Toast toast = Toast.makeText(getApplicationContext(), "Cannot beat person", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                    toast.show();
                    Intent returnIntent = new Intent(RandomEventActivity.this, UniverseGenerationActivity.class);
                    startActivity(returnIntent);
                }else{
                    Intent tradeIntent = new Intent(RandomEventActivity.this, MarketActivity.class);
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




    }
}
