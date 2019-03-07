package com.example.myapplication;

import android.content.Context;

import java.util.Random;

public class Asteroid extends SpaceBody{
    private int radius = 2; // радиус
    private float minSpeed = (float) 0.1; // минимальная скорость
    private float maxSpeed = (float) 0.5; // максимальная скорость

    public Asteroid(Context context) {
        Random random = new Random();
int re=random.nextInt(3);
       if (re>0) {
           switch (re) {
               case 1:
                   bitmapId = R.drawable.asteroid;
                   break;
               case 2:
                   bitmapId = R.drawable.asteroid_red;
               case 3:
                   bitmapId = R.drawable.asteroid_greean;
                   break;
           }
       }
       else
           bitmapId=R.drawable.asteroid;
        y=0;
        x = random.nextInt(GameView.maxX) - radius;
        int r=random.nextInt(5);
if(r>0)
        size = radius*r;
else
{size=radius*2;}
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }

    @Override
    public void update() {
        y += speed;

    }

    public boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((x+size) < shipX)||(x > (shipX+shipSize))||((y+size) < shipY)||(y > (shipY+shipSize)));
    }
}
