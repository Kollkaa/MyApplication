package com.example.minigames.SpaceWar;

import android.content.Context;

import com.example.minigames.R;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ADD.MyEventListner;
import ADD.isColision;

public class Asteroid extends SpaceBody {
    private int radius = 5;
    // радиус
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

    public Asteroid(Context context,int complexity,int wid,int hei) {
          hp=1;
        this.wid=wid;
        this.hei=hei;
     Random random = new Random();
     switch (complexity)
        {
            case 1:
                speed= 10;
                break;
            case 2:
                speed=15;
            case 3:
                speed=20;
                break;
        }
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
        x = random.nextInt(wid) - size;
        int r=random.nextInt(5);
if(r>0) {
    size = 15 *r;
    hp=r;
}
else
{size=radius*2;}


        init(context);
    }

    @Override
    public void update() {
        y += speed;

    }

    public boolean isCollision(Ship ship) {
        return !(((x+bitmap.getWidth()) < ship.getX())||(x > (ship.getX()+ship.bitmap.getWidth()))||((y+bitmap.getHeight()) < ship.getY())||(y > (ship.getY()+ship.bitmap.getHeight())));
    }
}
