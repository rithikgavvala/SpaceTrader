package com.example.codemonkeys.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationActivity extends AppCompatActivity {
    private ConfigurationViewModel viewModel;


    //Widgets used for binding and getting information
    private EditText playerNameField;
    private Spinner pilotSpinner;
    private Spinner fighterSpinner;
    private Spinner traderSpinner;
    private Spinner engineerSpinner;
    private Spinner difficultySpinner;

    //Data for player being set
    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //vamsi is great


        //grab the dialog widgets so we can get info for later
        playerNameField = findViewById(R.id.player_name_field);
        pilotSpinner = findViewById(R.id.pilot_spinner);
        fighterSpinner = findViewById(R.id.fighter_spinner);
        traderSpinner = findViewById(R.id.trader_spinner);
        engineerSpinner = findViewById(R.id.engineer_spinner);
        difficultySpinner = findViewById(R.id.difficulty_spinner);

        Button button = findViewById(R.id.add_button);

        List<String> skillState = new ArrayList<String>();
        skillState.add("0");
        skillState.add("1");
        skillState.add("2");
        skillState.add("3");
        skillState.add("4");

        List<String> difficulty = new ArrayList<String>();
        difficulty.add("Easy");
        difficulty.add("Medium");
        difficulty.add("Hard");

        //adapter to display the allowable numbers for the spinners
        ArrayAdapter<String> pilotAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillState);
        pilotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilotSpinner.setAdapter(pilotAdapter);

        ArrayAdapter<String> fighterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillState);
        fighterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fighterSpinner.setAdapter(fighterAdapter);

        ArrayAdapter<String> traderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillState);
        traderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        traderSpinner.setAdapter(traderAdapter);

        ArrayAdapter<String> engineerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillState);
        engineerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        engineerSpinner.setAdapter(engineerAdapter);

        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulty);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);



    }

    public void onAddPressed(View view){
    }


}
