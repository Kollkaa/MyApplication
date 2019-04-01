package FlipsBird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ADD.MyEventListner;
import ADD.isColision;

public class FlipsView  extends SurfaceView implements Runnable {
    public static int getWidth1() {
        return width1;
    }

    public static int getHeight1() {
        return height1;
    }
    private static int width1 = 0;
    private static int height1 = 0;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Bird bird;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private long startTime;
    private ArrayList<Rectangel> rectangles = new ArrayList<>(); // тут будут харанится астероиды
    private ArrayList<Rectangel> remove_rectangles = new ArrayList<>(); // тут будут харанится астероиды
    private final int ASTEROID_INTERVAL = 50;
    private int currentTime = 0;


    private long lastTime = System.currentTimeMillis();
    private boolean presed;
    private List<MyEventListner> myEventListners = new LinkedList<>();
    private int rocket_color;
    private int complexity;

    public FlipsView(Context context, int rocket_color, int complexity) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        Display display = ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        width1 = p.x;
        height1 = p.y;
        Log.d("width x height", width1 + " " + height1);
        gameThread = new Thread(this);
        gameThread.start();
        this.rocket_color = rocket_color;
        this.complexity = complexity;
    }


    private void checkIfNewRectangle() { // каждые 50 итераций добавляем новый астероид
        if (currentTime >= ASTEROID_INTERVAL) {
            Random random = new Random();
            Rectangel rectangel = new Rectangel(getContext(), complexity, getWidth1(), getHeight1());
            rectangel.addEvenListner(new MyEventListner() {
                @Override
                public void processEvent(isColision event) {
                    if (event.getSource() == null || event.getType() == null) {
                        return;
                    }
                    switch (event.getType()) {
                        case ShipColision:
                            Log.d("Ship", "destroy");

                            break;
                        case AsteroidColision:
                            Log.d("Asteroid", "destroy");
                            break;
                        default:
                            break;
                    }
                }
            });
            rectangles.add(rectangel);

            currentTime = 0;
        } else {
            currentTime++;
        }
    }


    private void checkCollision() throws IOException { // перебираем все астероиды и проверяем не касается ли один из них корабля

        for (Rectangel rectangel : rectangles) {
            if (rectangel.isCollision(bird)) {// игрок проиграл
                rectangel.notifyEvenListner(new isColision(rectangel, isColision.Type.ShipColision));
                notifyEvenListner(new isColision(this, isColision.Type.ShipColision));
                gameRunning = false; // останавливаем игру
                Log.d("myLogs", "finish");
                // TODO добавить анимацию взрыва
            }
        }
    }

    public void addEvenListner(MyEventListner myEventListner) {
        myEventListners.add(myEventListner);
    }

    public void notifyEvenListner(isColision isColision) {
        for (MyEventListner myevent : myEventListners) {
            myevent.processEvent(isColision);
        }
    }

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

            checkIfNewRectangle();
            control();
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        if (!firstTime) {
            bird.update();
            try {
                for (Rectangel rectangel : rectangles) {
                    rectangel.update();
                    if (rectangel.getY() > bird.getY()) {
                        rectangel.notifyEvenListner(new isColision(rectangel, isColision.Type.AsteroidColision));
                        remove_rectangles.add(rectangel);
                    }

                }
            } catch (Exception e) {
                Log.d("LogGameView", "exseption update rockets");
            }

        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface
            if (firstTime) { // инициализация при первом запуске
                firstTime = false;
                bird = new Bird(getContext(), getWidth1(), getHeight1()); // добавляем корабль
            }
                canvas = surfaceHolder.lockCanvas(); // закрываем canvas
                try {
                    canvas.drawColor(Color.YELLOW); // заполняем фон чёрным
                } catch (Exception e) {

                }
                bird.drow(paint, canvas); // рисуем корабль
                if (rectangles.size() > 0)
                    for (Rectangel rectangel : rectangles) { // рисуем астероиды

                        rectangel.drow(paint, canvas);
                    }


                surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
            }
        }
    }

