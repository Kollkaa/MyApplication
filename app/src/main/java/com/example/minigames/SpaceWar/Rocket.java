package com.example.minigames.SpaceWar;

import android.content.Context;

import com.example.minigames.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ADD.MyEventListner;
import ADD.isColision;

public class Rocket extends SpaceBody{

  private static int minSpeed =  10; // минимальная скорость

  private List<MyEventListner> myEventListners =new LinkedList<>();



  public void addEvenListner(MyEventListner myEventListner)
  { myEventListners.add(myEventListner);}
  public void notifyEvenListner(isColision isColision)
  {
    for (MyEventListner myevent: myEventListners)
    {myevent.processEvent(isColision);}
  }

  public Rocket(Context context,int rocket_color,Ship ship,int wid,int hei ) {
    Random random = new Random();
      this.wid=wid;
      this.hei=hei;
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


    y=ship.getY();
    x = ship.getX()+((ship.bitmap.getWidth()/2)-(ship.bitmap.getWidth()%2));

      size =30;

    speed = minSpeed;

    init(context);
  }

  @Override
  public void update() {
    y -= speed;

  }

  public boolean isCollision(Asteroid asteroid) {
    return !(((x+bitmap.getWidth()) < asteroid.getX())||(x > (asteroid.getX()+asteroid.bitmap.getWidth()))||((y+bitmap.getHeight()) < asteroid.getY())||(y > (asteroid.getY()+asteroid.bitmap.getHeight())));
  }
}
