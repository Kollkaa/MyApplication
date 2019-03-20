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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ADD.MyEventListner;
import ADD.isColision;

public class GameView extends SurfaceView implements Runnable{
    public static int maxX = 200; // размер по горизонтали
    public static int maxY = 100; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Ship ship;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private long startTime;
    private ArrayList<Rocket> remove_rocket = new ArrayList<>(); // тут будут харанится астероиды
    private ArrayList<Asteroid> remove_asteroids = new ArrayList<>(); // тут будут харанится астероиды
    private ArrayList<Asteroid> asteroids = new ArrayList<>(); // тут будут харанится астероиды
    private final int ASTEROID_INTERVAL = 50; // время через которое появляются астероиды (в итерациях)
    private final int ROCKET_INTERVAL=100;
    private int currentTime = 0 ;
    private int currentTime1 = 0 ;
    private ArrayList<Rocket> rockets = new ArrayList<>(); // тут будут харанится астероиды
    private long lastTime = System.currentTimeMillis();
    private boolean presed;
    private List<MyEventListner> myEventListners =new LinkedList<>();
    private int rocket_color;
    private int complexity;

    public void addEvenListner(MyEventListner myEventListner)
    { myEventListners.add(myEventListner);}
    public void notifyEvenListner(isColision isColision)
    {
        for (MyEventListner myevent: myEventListners)
        {myevent.processEvent(isColision);}
    }
    public GameView(Context context,int rocket_color,int complexity) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
        this.rocket_color=rocket_color;
        this.complexity=complexity;
    }
    public void setPresed(){
        try {

            Rocket rocket = new Rocket(getContext(), ship.x, ship.y,rocket_color);
            rocket.addEvenListner(new MyEventListner() {
                @Override
                public void processEvent(isColision event)  {
                    if(event.getSource()==null||event.getType()==null)
                    {return;}
                    switch (event.getType())
                    {
                        case RocketColision:
                            Log.d("Rocket","destroy");
                            break;
                        default:
                            break;
                    }
                }
            });
            rockets.add(rocket);


        }
        catch (Exception e)
        {Log.d("MyLog",e.getMessage());}
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }
    public boolean isGameRunning() {
        return gameRunning;
    }

    private int scores=0;


    @Override
    public void run() {
        while (gameRunning) {
            update();
            try {
                draw();
                checkCollision();
            } catch (IOException e) {
                e.printStackTrace();
            }

            checkIfNewAsteroid();
            control();
        }

    }

    private void update() {
        if (!firstTime) {
            ship.update();
            try {
                for (Asteroid asteroid : asteroids) {
                    asteroid.update();
                    if (asteroid.y > ship.y) {
                        asteroid.notifyEvenListner(new isColision(asteroid, isColision.Type.AsteroidColision));
                        remove_asteroids.add(asteroid);}

                }
            } catch (Exception e){Log.d("LogGameView", "exseption update rockets");}
            try {
                for (Rocket rocket : rockets) {
                    rocket.update();
                       if (rocket.y < 0) {
                           rocket.notifyEvenListner(new isColision(rocket, isColision.Type.RocketColision));
                           remove_rocket.add(rocket);
                      }
                    }
                } catch (Exception e){ Log.d("LogGameView", "exseption update rockets"); }
        }
    }

    private void draw() {
          if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface
               if (firstTime) { // инициализация при первом запуске
                   firstTime = false;
                   unitW = surfaceHolder.getSurfaceFrame().width() / maxX; // вычисляем число пикселей в юните
                   unitH = surfaceHolder.getSurfaceFrame().height() / maxY;
                   ship = new Ship(getContext()); // добавляем корабль
               }
               canvas = surfaceHolder.lockCanvas(); // закрываем canvas
              try {
                  canvas.drawColor(Color.BLACK); // заполняем фон чёрным
              }
              catch (Exception e)
              {

              }
               ship.drow(paint, canvas); // рисуем корабль
              if(asteroids.size()>0)
               for (Asteroid asteroid : asteroids) { // рисуем астероиды

                   asteroid.drow(paint, canvas);
               }
               try {
                   if (rockets.size()>0)
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

        for (Asteroid asteroid : asteroids) {
            if(asteroid.isCollision(ship.x, ship.y, ship.size)){// игрок проиграл
                asteroid.notifyEvenListner(new isColision(asteroid,isColision.Type.ShipColision));
                notifyEvenListner(new isColision(this,isColision.Type.ShipColision));
                gameRunning = false; // останавливаем игру
                Log.d("myLogs","finish");
                // TODO добавить анимацию взрыва
            }
            if(rockets.size()>0)
           try {
               for(Rocket rocket:rockets)
               {
                   if (rocket.isCollision(asteroid.x,asteroid.y,asteroid.size)) {

                       asteroid.notifyEvenListner(new isColision(asteroid, isColision.Type.AsteroidColision));
                       rocket.notifyEvenListner(new isColision(rocket, isColision.Type.RocketColision));
                       notifyEvenListner(new isColision(this,isColision.Type.RocketKillAsteroid));
                       remove_asteroids.add(asteroid);
                       remove_rocket.add(rocket);

                   }
               }
           }
           catch (Exception e)
           {Log.d("LogCheckCollision","Rockets error");}
        }
        for (Asteroid ast:remove_asteroids)
        {asteroids.remove(ast);}
        for (Rocket rock:remove_rocket)
        {rockets.remove(rock);}
        remove_asteroids=new ArrayList<>();
        remove_rocket=new ArrayList<>();



    }

    private void checkIfNewAsteroid(){ // каждые 50 итераций добавляем новый астероид
        if(currentTime >= ASTEROID_INTERVAL){
            Random random=new Random();
            Asteroid asteroid = new Asteroid(getContext(),complexity);
            asteroid.addEvenListner(new MyEventListner() {
                @Override
                public void processEvent(isColision event)  {
                    if(event.getSource()==null||event.getType()==null)
                    {return;}
                    switch (event.getType())
                    {
                        case ShipColision:
                            Log.d("Ship","destroy");

                            break;
                            case AsteroidColision:
                            Log.d("Asteroid","destroy");
                            break;
                        default:
                            break;
                    }
                }
            });
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
    public boolean isPresed() {
        return presed;
    }
    public void setPresed(boolean presed) {
        this.presed = presed;
    }
}
