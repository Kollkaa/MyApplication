package com.example.myapplication;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ADD.CustomDialogFragment;
import ADD.MyEventListner;
import ADD.isColision;

public class GameFirstExrActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    public static boolean isLeftPressed = false; // нажата левая кнопка
    public static boolean isRightPressed = false; // нажата правая кнопка
    int scores=0;
    GameView gameView;
    TextView text_score;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_first_exr);
        gameView= new GameView(this); // создаём gameView
        text_score=(TextView)findViewById(R.id.text_score);
        gameView.addEvenListner(new MyEventListner() {
            @Override
            public void processEvent(isColision event) {
                if(event.getSource()==null||event.getType()==null)
                {return;}
                switch (event.getType())
                {
                    case ShipColision:
                        Log.d("Game","finish");
                        CustomDialogFragment dialog = new CustomDialogFragment();
                        Bundle args = new Bundle();
                        args.putString("name", "kolia");
                        args.putInt("scores",scores);
                        dialog.setArguments(args);
                        try {
                                       dialog.show(getSupportFragmentManager(), "custom");
                        }
                            catch (Exception e){Log.d("Dialog","error");}
                        break;
                    case RocketKillAsteroid: {
                        Log.d("Rocket", "kill Asteroid");
                        scores++;
                        text_score.setText("Досвід: " + scores);
                        text_score.invalidate();
                        text_score.requestLayout();
                    }
                        break;
                    default:
                        break;
                }
            }
        });

        RelativeLayout gameLayout = (RelativeLayout) findViewById(R.id.relative_startgame); // находим gameLayout
        gameLayout.addView(gameView); // и добавляем в него gameView
        Button leftButton = (Button) findViewById(R.id.left); // находим кнопки
        Button rightButton = (Button) findViewById(R.id.right);
        Button attackButton=(Button)findViewById(R.id.attack) ;
        leftButton.setOnTouchListener(this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)
        rightButton.setOnTouchListener(this);
        attackButton.setOnTouchListener(this);


    }
    @Override
    public void onClick(View v) {

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        text_score.setText("Досвід: "+scores);
        final Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(scores), Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 500);
        return super.dispatchTouchEvent(ev);
    }

    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { // определяем какая кнопка
            case R.id.left:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.right:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
                break;
            case R.id.attack:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        gameView.setPresed();
                        break;

                }
                break;

        }
        return true;
    }

}
