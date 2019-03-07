package com.example.myapplication;

import android.content.Context;

import java.util.Random;

public class Rocket extends SpaceBody{
  private int radius = 2; // радиус
  private float minSpeed = (float) 0.1; // минимальная скорость
  private float maxSpeed = (float) 0.5; // максимальная скорость

  public Rocket(Context context,float shipX,float shipY ) {
    Random random = new Random();
    int re=random.nextInt(3);

      bitmapId=R.drawable.rocket;
    y=shipY;
    x = shipX+(float) 2.5;

      size = radius*1;

    speed = maxSpeed;

    init(context);
  }

  @Override
  public void update() {
    y -= speed;

  }

  public boolean isCollision(float shipX, float shipY, float shipSize) {
    return !(((x+size) < shipX)||(x > (shipX+shipSize))||((y+size) < shipY)||(y > (shipY+shipSize)));
  }
}
