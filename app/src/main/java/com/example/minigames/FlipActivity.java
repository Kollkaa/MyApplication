package com.example.minigames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class FlipActivity extends AppCompatActivity {
    public static boolean clickup=false;
    public static boolean clickdown=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flip);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) { // определяем нажата или отпущена
            case MotionEvent.ACTION_DOWN:
                clickup = true;
                break;
            case MotionEvent.ACTION_UP:
                clickdown=true;

                break;
        }

        return super.dispatchTouchEvent(ev);
    }
}
