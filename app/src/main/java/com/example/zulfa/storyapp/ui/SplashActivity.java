package com.example.zulfa.storyapp.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zulfa.storyapp.R;

public class SplashActivity extends AppCompatActivity {
            private static int SplashTime = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent = new Intent(SplashActivity.this,MainActivity.class);
                   startActivity(splashintent);
                   finish();

            }
        }, SplashTime);
    }
}
