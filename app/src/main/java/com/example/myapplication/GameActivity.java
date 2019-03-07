package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start);

        rl=(RelativeLayout)findViewById(R.id.relative_lay_game);
        AnimationDrawable animationDrawable =(AnimationDrawable)rl.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }

    @Override
    public void onClick(View v) {
        Animation animation= AnimationUtils.loadAnimation(GameActivity.this,R.anim.sample_anim);
        v.startAnimation(animation);
        switch (v.getId()) {
            case R.id.start_game:
                Intent intent = new Intent(this, GameFirstExrActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}
