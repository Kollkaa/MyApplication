package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        rl=(RelativeLayout)findViewById(R.id.relative_lay_menu);
        AnimationDrawable animationDrawable =(AnimationDrawable)rl.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }

    @Override
    public void onClick(View v) {
        Animation animation= AnimationUtils.loadAnimation(MenuActivity.this,R.anim.sample_anim);
        v.startAnimation(animation);
        switch (v.getId()) {

            case R.id.scores_menu:
                intent = new Intent(this, ScoresActivity.class);
                intent.putExtra("activity",0);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "scores!",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.help_menu:
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "help",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_menu:
                 intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "setting",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.game_menu:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "game_start",
                        Toast.LENGTH_SHORT).show();
                break;
                default:

                break;
        }
    }


}
