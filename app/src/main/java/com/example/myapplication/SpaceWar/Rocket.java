package com.example.myapplication.SpaceWar;

import android.content.Context;

import com.example.myapplication.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ADD.MyEventListner;
import ADD.isColision;

public class Rocket extends SpaceBody{
  private int radius = 3; // радиус
  private float minSpeed = (float) 0.1; // минимальная скорость
  private float maxSpeed = (float) radius/2; // максимальная скорость
  private List<MyEventListner> myEventListners =new LinkedList<>();



  public void addEvenListner(MyEventListner myEventListner)
  { myEventListners.add(myEventListner);}
  public void notifyEvenListner(isColision isColision)
  {
    for (MyEventListner myevent: myEventListners)
    {myevent.processEvent(isColision);}
  }
  public Rocket(Context context,float shipX,float shipY,int rocket_color ) {
    Random random = new Random();
    int re=random.nextInt(3);
switch (rocket_color){
    case 1:
        bitmapId= R.drawable.lazer;
        break;
        case 2:
                bitmapId=R.drawable.rocket_blue;
    break ;
    default:
        break;
}


    y=shipY;
    x = shipX+7.5f;

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
