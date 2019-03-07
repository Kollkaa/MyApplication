package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameFirstExrActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    public static boolean isLeftPressed = false; // нажата левая кнопка
    public static boolean isRightPressed = false; // нажата правая кнопка

    GameView gameView;
    TextView text_scores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_first_exr);
        gameView= new GameView(this); // создаём gameView
text_scores=(TextView)findViewById(R.id.text_scores);
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
      if(!gameView.isGameRunning()) {
          Intent intent = new Intent(this, ScoresActivity.class);
          intent.putExtra("name","kolia");
          intent.putExtra("scores",gameView.getScores());
          startActivity(intent);
      }
        text_scores.setText("Досвід: "+gameView.getScores());
        final Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(gameView.getScores()), Toast.LENGTH_SHORT);
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
                gameView.setPresed(true,System.currentTimeMillis());
                final Toast toast = Toast.makeText(getApplicationContext(), "atach", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 500);
                break;

        }
        return true;
    }

}
