package com.example.codemonkeys.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.codemonkeys.R;


public class MainActivity extends AppCompatActivity{

    private ImageView rocketShip;
    private ImageView moon;
    private ImageView title;
    private Button start;

    @Override
    protected void onStart() {
        super.onStart();
        moon.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);
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
        start =  findViewById(R.id.button_start_game);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rocketShip.startAnimation(animation);


            }
        });


    }
    private class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            moon.setVisibility(View.INVISIBLE);
            title.setVisibility(View.INVISIBLE);
            start.setVisibility(View.INVISIBLE);

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
            startActivity(intent);
            rocketShip.setVisibility(View.INVISIBLE);



        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }



}


