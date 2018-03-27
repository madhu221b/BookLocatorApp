package com.pathak.rajat.pictlibrary;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private static  int SPLASH_TIME_OUT = 3000;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        imageView = (ImageView)findViewById(R.id.image);


        AlphaAnimation blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        blinkanimation.setDuration(700); // duration - half a second
        blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        blinkanimation.setRepeatCount(5); // Repeat animation infinitely
        blinkanimation.setRepeatMode(Animation.REVERSE);

        imageView.startAnimation(blinkanimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);

                finish();
            }
        },SPLASH_TIME_OUT);
    }
}



