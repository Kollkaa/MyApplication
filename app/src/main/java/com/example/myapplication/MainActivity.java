package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


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
            default:
                break;
        }
    }
}
