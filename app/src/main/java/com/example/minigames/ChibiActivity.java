package com.example.minigames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.minigames.AdventureWorld.GameSurface;

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

public class ChibiActivity extends AppCompatActivity implements  View.OnClickListener  {

    private static final String FILENAME ="scores_adventure_world" ;
    private  ArrayList<String> arr=new ArrayList<>();
    private int score=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

GameSurface gameSurface=new GameSurface(this);
       gameSurface.addEvenListner(new MyEventListner() {
           @Override
           public void processEvent(isColision event) {
               if (event.getSource() == null || event.getType() == null) {
                   return;
               }
               switch (event.getType()) {
                   case AllChebiKilled:
                       Log.d("Game", "finish");
                       CustomDialogFragment dialog = new CustomDialogFragment();
                       Bundle args = new Bundle();
                       args.putString("name", "kolia");
                       args.putInt("scores", score);
                       args.putString("type_game","scores_adventure_world");


                       dialog.setArguments(args);
                       try {
                           dialog.show(getSupportFragmentManager(), "custom");
                       } catch (Exception e) {
                           Log.d("Dialog", "error");
                       }
                       try {
                           Thread.sleep(2000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       readFile();
                       writeFile(arr.size(), "kolia", score);

                       break;
                   case ChibiKilled: {
                       Log.d("Chibi", "kill chibi");
                       score++;

                   }
                   break;
                   default:
                       break;
               }


           }
       });
        this.setContentView(gameSurface);
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

    @Override
    public void onClick(View v) {

    }
}
