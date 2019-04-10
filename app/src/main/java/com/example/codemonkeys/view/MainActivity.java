package com.example.codemonkeys.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.DataCallback;
import com.example.codemonkeys.model.Model;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.model.Universe;


public class MainActivity extends AppCompatActivity{

    private ImageView rocketShip;
    private ImageView moon;
    private ImageView title;
    private Button newButton;
    private Button loadButton;
    boolean load;

    @Override
    protected void onStart() {
        super.onStart();
        moon.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        newButton.setVisibility(View.VISIBLE);
        rocketShip.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        rocketShip = findViewById(R.id.rocketShip);
        moon = findViewById(R.id.moon);
        title = findViewById(R.id.title);

        final TranslateAnimation animation = new TranslateAnimation(0,0, 0, -2000);
        animation.setDuration(1500);
        animation.setFillAfter(false);
        animation.setAnimationListener(new MyAnimationListener());


        //This code sets up our button at bottom of screen to add a new course
        newButton =  findViewById(R.id.new_button);
        loadButton = findViewById(R.id.load_button);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rocketShip.startAnimation(animation);


            }
        });
        loadButton.setOnClickListener((view) -> {
            load = true;
            Model.getInstance().load(new DataCallback() {
                @Override
                public void onDataCallback(Player p, Universe u) {
                    Log.i("callback", "PLAYER AND UNIVERSE ARE HERE WOO " + p + " " + u.getPlanets());

                    rocketShip.startAnimation(animation);
                }

            });
        });




    }
    private class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            moon.setVisibility(View.INVISIBLE);
            title.setVisibility(View.INVISIBLE);
            newButton.setVisibility(View.INVISIBLE);
            loadButton.setVisibility(View.INVISIBLE);

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
            if(load == true) {
                intent = new Intent(MainActivity.this, UniverseGenerationActivity.class);
            }
            startActivity(intent);
            rocketShip.setVisibility(View.INVISIBLE);



        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


}


