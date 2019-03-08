package com.example.myapplication;

import android.content.Context;

import java.util.Random;

public class Rocket extends SpaceBody{
  private int radius = 2; // радиус
  private float minSpeed = (float) 0.1; // минимальная скорость
  private float maxSpeed = (float) 0.6; // максимальная скорость

  public Rocket(Context context,float shipX,float shipY ) {
    Random random = new Random();
    int re=random.nextInt(3);

      bitmapId=R.drawable.rocket;
    y=shipY;
    x = shipX+(float) 1.5;

      size = radius*1;

    speed = maxSpeed;

    init(context);
  }

  @Override
  public void update() {
    y -= speed;

  }

  public boolean isCollision(float asteroidX, float asteroidY, float asteroidsize) {
    return !(((x+size) < asteroidX)||(x > (asteroidX+asteroidsize))||((y+size) < asteroidY)||(y > (asteroidY+asteroidsize)));
  }
}
