package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.model.Universe;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationActivity extends AppCompatActivity{
    private ConfigurationViewModel viewModel;


    //Widgets used for binding and getting information
    private EditText playerNameField;
    private SeekBar fighterBar;
    private SeekBar traderBar;
    private SeekBar engineerBar;
    private SeekBar pilotBar;
    private Spinner difficultySpinner;
    private TextView pilotSkillTextView;
    private TextView fighterSkillTextView;
    private TextView traderSkillTextView;
    private TextView engineerSkillTextView;
    private int fighterProgress;
    private int traderProgress;
    private int engineerProgress;
    private int pilotProgress;
    private ImageView rocketShip;
    private ImageView moon;
    private ImageView title;
    private Button start;

    private SeekBar.OnSeekBarChangeListener mlistener;



    //Data for player being set
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.configuration_activity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

       //grab the dialog widgets so we can get info for later
         playerNameField = findViewById(R.id.player_name_field);
//         pilotSpinner = findViewById(R.id.pilot_spinner);
         fighterBar = findViewById(R.id.fighter_bar);
         traderBar = findViewById(R.id.trader_bar);
         engineerBar = findViewById(R.id.engineer_bar);
         difficultySpinner = (Spinner) findViewById(R.id.difficulty_spinner);
         pilotBar = findViewById(R.id.pilot_bar);

         pilotSkillTextView = findViewById(R.id.pilotSkillTextView);
         fighterSkillTextView = findViewById(R.id.fighterSkillTextView);
         traderSkillTextView = findViewById(R.id.traderSkillTestView);
         engineerSkillTextView = findViewById(R.id.engineerSkillTextView);


         mlistener = new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 int id = seekBar.getId();
                 if(id == R.id.pilot_bar) {
                     pilotProgress = progress;
                     pilotSkillTextView.setText("" + progress);
                 } else if(id == R.id.fighter_bar) {
                     fighterProgress = progress;
                     fighterSkillTextView.setText("" + progress);
                 } else if(id == R.id.trader_bar) {
                     traderProgress = progress;
                     traderSkillTextView.setText("" + progress);
                 } else if(id == R.id.engineer_bar) {
                     engineerProgress = progress;
                     engineerSkillTextView.setText("" + progress);
                 }
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         };
         fighterBar.setOnSeekBarChangeListener(mlistener);
        traderBar.setOnSeekBarChangeListener(mlistener);
        engineerBar.setOnSeekBarChangeListener(mlistener);
        pilotBar.setOnSeekBarChangeListener(mlistener);


        Button button = findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = playerNameField.getText().toString();
                String difficulty = difficultySpinner.getSelectedItem().toString();
                int sum = pilotProgress + fighterProgress + traderProgress + engineerProgress;
                if(sum == 16) {
                    player = new Player(name, pilotProgress, fighterProgress, traderProgress, engineerProgress,
                            difficulty);
                    viewModel.updatePlayer(player);
                    Toast toast = Toast.makeText(v.getContext(), "Created: " + name, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                    toast.show();
                    Universe universe = Universe.getInstance();
                    universe.generateUniverse();
                } else {
                    Toast toast = Toast.makeText(v.getContext(),
                            "Skill points must add to 16", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                    toast.show();
                }

                Intent intent = new Intent(ConfigurationActivity.this, UniverseGenerationActivity.class);
                startActivity(intent);


                // Code here executes on main thread after user presses button
            }
        });


        String[] skillState = new String[]{"0","1","2","3","4"};


        List<String> difficulty = new ArrayList<String>();
        difficulty.add("Easy");
        difficulty.add("Medium");
        difficulty.add("Hard");

        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,difficulty);

        difficultySpinner.setAdapter(difficultyAdapter);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }


}
