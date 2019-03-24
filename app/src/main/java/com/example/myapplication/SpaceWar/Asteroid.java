package com.example.myapplication.SpaceWar;

import android.content.Context;

import com.example.myapplication.R;
import com.example.myapplication.SpaceBody;

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

    public Asteroid(Context context,int complexity) {
          hp=1;
     Random random = new Random();
     switch (complexity)
        {
            case 1:
                speed=(float) 0.334*3f;
                break;
            case 2:
                speed=(float) 0.543*3f;
            case 3:
                speed=(float) 0.7123123*3f;
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
        x = random.nextInt(GameView.maxX) - radius;
        int r=random.nextInt(5);
if(r>0) {
    size = radius * r;
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

    public boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((x+size) < shipX)||(x > (shipX+shipSize))||((y+size) < shipY)||(y > (shipY+shipSize)));
    }
}
