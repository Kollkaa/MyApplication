package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.myapplication.R;
import com.example.myapplication.SpaceBody;

public class Rocket extends SpaceBody {
    private int radius = 2; // радиус
    private float speed = (float) 0.3; //  скорость
        public Rocket(Context context, float shipX, float shipY) {
            bitmapId=R.drawable.rocket;
            y=shipY;
            x = shipX - radius;
            size=radius*1;
            speed+=5;

            init(context);
        }

        @Override
        public void update() {
            y -= speed;

        }

    @Override
    public void drow(Paint paint, Canvas canvas) {
        super.drow(paint, canvas);
    }

    public boolean isCollision(float asteroidX, float asteroidY, float asteroidSize) {
            return !(((x+size) < asteroidX)||(x > (asteroidX+asteroidSize))||((y+size) < asteroidY)||(y > (asteroidY+asteroidSize)));
        }
}
