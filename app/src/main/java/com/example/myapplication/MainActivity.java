package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);




    }

    @Override
    public void onClick(View v) {
        Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.sample_anim);
        v.startAnimation(animation);
        switch (v.getId()) {
            case R.id.main_text:
                intent = new Intent(this, SplashActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "scores!",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.gifImageView:
                intent = new Intent(this, SplashActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "scores!",
                        Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}