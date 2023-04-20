package com.example.my_weatherapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.my_weatherapp.R;

public class SplashScreen extends AppCompatActivity {
    Handler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        handler.postDelayed(runnable,2000);


    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }
    };
}