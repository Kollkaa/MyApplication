package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    RelativeLayout rl;
    Button re;
    int complexity=1;
    int color=1;
    String[] col = {"Червона ракета", "Синя ракета"};
    String[] comple = {"Легко", "Нормально", "Важко"};
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game_start);
        re=(Button)findViewById(R.id.start_game);

        rl=(RelativeLayout)findViewById(R.id.relative_lay_game);

        AnimationDrawable animationDrawable =(AnimationDrawable)rl.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();


        /** Called when the activity is first created. */


            // адаптер
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, col);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setAdapter(adapter);
            spinner.setPrompt("Тип ракет");

            // устанавливаем обработчик нажатия
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                   color=position+1;
                    re.setEnabled(true);
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    color=1;

                    re.setEnabled(false);
                }
            });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, comple);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter1);
        spinner2.setPrompt("Складність");

        // устанавливаем обработчик нажатия
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                complexity=position+1;

                re.setEnabled(true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                complexity=1;

            }
        });
    }

    @Override
    public void onClick(View v) {

        Animation animation = AnimationUtils.loadAnimation(GameActivity.this, R.anim.sample_anim);
        v.startAnimation(animation);
        switch (v.getId()) {
            case R.id.start_game:
                Intent intent = new Intent(this, GameFirstExrActivity.class);
                intent.putExtra("rocket_color", this.color);
                intent.putExtra("complexity", this.complexity);
                startActivity(intent);
                break;
            case R.id.start_game2:
                 intent = new Intent(this, ChibiActivity.class);
                intent.putExtra("rocket_color", this.color);
                intent.putExtra("complexity", this.complexity);
                startActivity(intent);
                break;
                            default:
                break;
        }
    }


    }



