package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable{
    public static int maxX = 40; // размер по горизонтали
    public static int maxY = 56; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
    private boolean firstTime = true;

    public boolean isGameRunning() {
        return gameRunning;
    }

    private boolean gameRunning = true;
    private Ship ship;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private long startTime;

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }
    private ArrayList<Rocket> remove_rocket = new ArrayList<>(); // тут будут харанится астероиды

    private ArrayList<Asteroid> remove_asteroids = new ArrayList<>(); // тут будут харанится астероиды
    private ArrayList<Asteroid> asteroids = new ArrayList<>(); // тут будут харанится астероиды
    private final int ASTEROID_INTERVAL = 50; // время через которое появляются астероиды (в итерациях)
    private final int ROCKET_INTERVAL=100;
    private int currentTime = 0 ;
    private int currentTime1 = 0 ;
    private ArrayList<Rocket> rockets = new ArrayList<>(); // тут будут харанится астероиды
    private long lastTime = System.currentTimeMillis();

    public void setPresed(boolean presed,long nowTime ){
        try {
            this.presed = presed;
            if (presed) {
                if (nowTime - lastTime >= 0.1) {
                    Rocket rocket = new Rocket(getContext(), ship.x, ship.y);
                    rockets.add(rocket);
                    lastTime = nowTime;
                } else {
                    System.out.print("Time left" + String.valueOf(0.1 - nowTime));
                }
            }
        }
        catch (Exception e)
        {Log.d("MyLog",e.getMessage());}
    }

    private boolean presed;


    private int scores=0;

    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            try {
                checkCollision();
            } catch (IOException e) {
                e.printStackTrace();
            }

            checkIfNewAsteroid();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            ship.update();
            if (rockets.size()>0)
            for (Rocket rocket:rockets)
            {rocket.update();}
            if (asteroids.size()>0)
            for (Asteroid asteroid : asteroids) {

                asteroid.update();
                if(asteroid.y>ship.y)
                {
                    remove_asteroids.add(asteroid);
                }

            }
            for (int j=0;j<remove_asteroids.size();j++)
            {
                if ( asteroids.remove(remove_asteroids.get(j)))
                {}

            }
        }    }

    private void draw() {

           if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

               if (firstTime) { // инициализация при первом запуске
                   firstTime = false;
                   unitW = surfaceHolder.getSurfaceFrame().width() / maxX; // вычисляем число пикселей в юните
                   unitH = surfaceHolder.getSurfaceFrame().height() / maxY;
                   ship = new Ship(getContext()); // добавляем корабль
               }
               canvas = surfaceHolder.lockCanvas(); // закрываем canvas
               canvas.drawColor(Color.BLACK); // заполняем фон чёрным
               ship.drow(paint, canvas); // рисуем корабль
               for (Asteroid asteroid : asteroids) { // рисуем астероиды
                   asteroid.drow(paint, canvas);
               }
               try {
                   for (Rocket rocket : rockets) //рисуем ракету
                   {
                       rocket.drow(paint, canvas);
                   }
               }catch (Exception e)
               {Log.d("LogGameView","exseption draw rockets");}

               surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
           }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision() throws IOException { // перебираем все астероиды и проверяем не касается ли один из них корабля
       ArrayList<Asteroid>destroy_aster=new ArrayList<>();
       ArrayList<Rocket>destroy_rocket=new ArrayList<>();
        for (Asteroid asteroid : asteroids) {
            if(asteroid.isCollision(ship.x, ship.y, ship.size)){
                // игрок проиграл
                gameRunning = false; // останавливаем игру


                Log.d("myLogs","error");

                // TODO добавить анимацию взрыва
            }
            if(rockets.size()>0)
           try {
               for(Rocket rocket:rockets)
               {
                   if (rocket.isCollision(asteroid.x,asteroid.y,asteroid.size)) {
                       destroy_aster.add(asteroid);
                       destroy_rocket.add(rocket);
                       scores++;
                   }
               }
           }
           catch (Exception e)
           {Log.d("LogCheckCollision","Rockets error");}
        }
        for (Asteroid ast:destroy_aster)
        {asteroids.remove(ast);}
        for (Rocket rock:destroy_rocket)
        {asteroids.remove(rock);}
        destroy_rocket=new ArrayList<>();
        rockets=new ArrayList<>();
        destroy_aster=new ArrayList<>();


    }
     private  void atack(boolean press) {



     }
    private void checkIfNewAsteroid(){ // каждые 50 итераций добавляем новый астероид
        if(currentTime >= ASTEROID_INTERVAL){
            Random random=new Random();
            Asteroid asteroid = new Asteroid(getContext());
            asteroids.add(asteroid);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }

    public int getScores() {
        return scores;
    }
    public void setScores(int scores) {
        this.scores = scores++;
    }
}
