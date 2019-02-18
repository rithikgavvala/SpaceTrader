package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.model.Repository;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //vamsi is great
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }


        //grab the dialog widgets so we can get info for later
//        playerNameField = findViewById(R.id.player_name_field);
//        pilotSpinner = findViewById(R.id.pilot_spinner);
//        fighterSpinner = findViewById(R.id.fighter_spinner);
//        traderSpinner = findViewById(R.id.trader_spinner);
//        engineerSpinner = findViewById(R.id.engineer_spinner);
        difficultySpinner = findViewById(R.id.difficulty_spinner);

       // Button button = findViewById(R.id.add_button);


        String[] skillState = new String[]{"0","1","2","3","4"};


        List<String> difficulty = new ArrayList<String>();
        difficulty.add("Easy");
        difficulty.add("Medium");
        difficulty.add("Hard");

        //adapter to display the allowable numbers for the spinners
//        ArrayAdapter<String> pilotAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillState);
//        pilotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        pilotSpinner.setAdapter(pilotAdapter);
//
//        ArrayAdapter<String> fighterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillState);
//        fighterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        fighterSpinner.setAdapter(fighterAdapter);
//
//        ArrayAdapter<String> traderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillState);
//        traderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        traderSpinner.setAdapter(traderAdapter);
//
//        ArrayAdapter<String> engineerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillState);
//        engineerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        engineerSpinner.setAdapter(engineerAdapter);

        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulty);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }


    public void onAddPressed(View view) {
        finish();
    }


}
