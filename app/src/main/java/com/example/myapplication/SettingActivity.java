package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        rl=(RelativeLayout)findViewById(R.id.relative_lay_setting);
        AnimationDrawable animationDrawable =(AnimationDrawable)rl.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

    }


    @Override
    public void onClick(View v) {
        Animation animation= AnimationUtils.loadAnimation(SettingActivity.this,R.anim.sample_anim);
        v.startAnimation(animation);
        switch (v.getId()) {
            case R.id.menu_setting:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);



                break;
            default:
                break;
        }
    }





}
