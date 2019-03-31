package com.example.minigames;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class ScoresActivity extends AppCompatActivity implements View.OnClickListener  {
  private final String LOG_TAG = "myLogs";
  private String  FILENAME = "";
  private ArrayList<String> arr=new ArrayList<>();
  private TextView mSelectText;
  RelativeLayout rl;
  TableLayout tl;
  @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      Bundle arguments = getIntent().getExtras();
      String name = "";
      FILENAME=arguments.getString("type_game");
      int scores = 0;
      readFile();

      if (arguments.getInt("activity")==1)
      if (arguments != null) {
        name = arguments.getString("name");
        scores = arguments.getInt("scores");
        writeFile(arr.size(), name, scores);
      }

      this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);

      // Set No Title
      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      setContentView(R.layout.scores);

      tl = (TableLayout) findViewById(R.id.tableloyught);
      rl = (RelativeLayout) findViewById(R.id.relative_lay_scores);
      AnimationDrawable animationDrawable = (AnimationDrawable) rl.getBackground();
      animationDrawable.setEnterFadeDuration(5000);
      animationDrawable.setExitFadeDuration(2000);
      animationDrawable.start();


      for (String ar : arr) {
        try {
          addRow(tl, ar.split(" ")[0], ar.split(" ")[1], ar.split(" ")[2]);
        } catch (Exception e) {
          Log.d(LOG_TAG, e.getMessage());
        }
      }

      if(arguments.getInt("activity")==1)try {
        addRow(tl, String.valueOf(arr.size()), name, String.valueOf(scores));
      }
      catch (Exception e)
      {Log.d(LOG_TAG,e.getMessage());}

    }
    catch (Exception e)
    {Log.d(LOG_TAG,e.getMessage());}


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
      Log.d(LOG_TAG, "Файл записан"+FILENAME);
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
        Log.d(LOG_TAG, str);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addRow(TableLayout tableLayout,String number,String name,String scores) {
    TableRow rt=new TableRow(this);
    rt.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

    TextView number1=new TextView(this);
    TextView name1=new TextView(this);
    TextView scores1=new TextView(this);
    number1.setText(number);
    name1.setText(name);
    scores1.setText(scores);

    rt.addView(number1);
    rt.addView(name1);
    rt.addView(scores1);
    tableLayout.addView(rt); //добавляем созданную строку в таблицу
  }

  @Override
  public void onClick(View v) {
    Animation animation= AnimationUtils.loadAnimation(ScoresActivity.this,R.anim.sample_anim);
    v.startAnimation(animation);
    switch (v.getId()) {
      case R.id.menu_scores:
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);


        break;
      default:
        break;
    }
  }

}