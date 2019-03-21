package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.help);
        rl=(RelativeLayout)findViewById(R.id.relativeLayout_help);

        AnimationDrawable animationDrawable =(AnimationDrawable)rl.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }


    @Override
    public void onClick(View v) {
        Animation animation= AnimationUtils.loadAnimation(HelpActivity.this,R.anim.sample_anim);
        v.startAnimation(animation);
        switch (v.getId()) {
            case R.id.hyper_adress:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")));
                break;
            case R.id.menu_help:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                closeActivity();


                break;
            default:
                break;
        }
    }

    private void closeActivity() {
        this.finish();
    }
}
