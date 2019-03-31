package com.example.minigames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                openFileOutput("scores_space_war", MODE_APPEND)));
            BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("scores_adventure_world", MODE_APPEND)));) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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