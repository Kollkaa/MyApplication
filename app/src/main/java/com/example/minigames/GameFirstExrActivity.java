package com.example.minigames;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minigames.SpaceWar.GameView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import ADD.CustomDialogFragment;
import ADD.MyEventListner;
import ADD.isColision;

public class GameFirstExrActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    public static boolean isLeftPressed = false; // нажата левая кнопка
    public static boolean isRightPressed = false; // нажата правая кнопка
    public  static  boolean mouseClick=false;
    public  static float mousex=0f;
    public  static float mousey=0f;
   private int score=0;
    GameView gameView;
    TextView text_score;
    int color=1;
    int complexity=1;
    final  String  FILENAME = "scores_space_war";
    ArrayList<String> arr=new ArrayList<>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
            if (arguments != null) {
            color = arguments.getInt("rocket_color");
            complexity=arguments.getInt("complexity");
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game_first_exr);

        gameView= new GameView(this,color,complexity); // создаём gameView
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
                        args.putInt("scores",score);
                        args.putString("type_game","scores_space_war");
                        dialog.setArguments(args);
                        try {
                                       dialog.show(getSupportFragmentManager(), "custom");
                        }

                            catch (Exception e){Log.d("Dialog","error");}
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!dialog.getToScores()) {
                            readFile();
                            writeFile(arr.size(), "kolia", score);
                        }
                        break;
                    case RocketKillAsteroid: {
                        Log.d("Rocket", "kill Asteroid");
                        score++;

                    }
                        break;
                    default:
                        break;
                }
            }
        });

        RelativeLayout gameLayout = (RelativeLayout) findViewById(R.id.relative_startgame); // находим gameLayout
        gameLayout.addView(gameView); // и добавляем в него gameView

        Button attackButton=(Button)findViewById(R.id.attack) ;

        attackButton.setOnTouchListener(this);


    }
    @Override
    public void onClick(View v) {
      if(v.getId()==R.id.toGameActivity) {
          Intent intent = new Intent(this, GameActivity.class);
          startActivity(intent);
          this.finish();
      }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) { // определяем нажата или отпущена
            case MotionEvent.ACTION_DOWN:
                mouseClick = true;
                mousex=ev.getX();
                Log.d("mouse",String.valueOf(mousex));
                mousey=ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                mouseClick = false;
                break;
        }
        final Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(score), Toast.LENGTH_SHORT);
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

    void writeFile(int number,String name,int scores) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_APPEND)));
            // пишем данные

            bw.append("\n"+number+" "+name+" "+scores);
            bw.flush();
            // закрываем поток
            bw.close();
            Log.d("my log", "Файл записан"+FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFile() {
        try {
            arr=new ArrayList<>();
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                arr.add(str);
                Log.d("my log", str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
