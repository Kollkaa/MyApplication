package com.example.myapplication.AdventureWorld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.myapplication.GameObject;
import com.example.myapplication.GameSurface;
import com.example.myapplication.R;

import java.util.Random;

public class Tree extends GameObject {
    private GameSurface gameSurface;
    private int wid;
    private int hei;
    private static Random random=new Random();
    public Tree(GameSurface gameSurface,Context context, int x, int y) {
                super(x/12,y/12, (x-x/12)-random.nextInt(x), (y-y/12)-random.nextInt(y));
        this.gameSurface= gameSurface;
        this.wid=x;
        this.hei=y;
        init(context);
    }
    public void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tree);
        image = Bitmap.createScaledBitmap(
                cBitmap, width, height, false);
        cBitmap.recycle();
    }

    public void draw(Canvas canvas, Paint paint) {
        if (x == wid - 100)
        {       if (y == 100)
                canvas.drawBitmap(image, x - 100, y, paint);
    }
        if (x == 0)
        {       if (y == 100)
            canvas.drawBitmap(image, x +100, y+100, paint);
        }
        if (x == wid-(wid/10) - 100)
        {       if (y == hei-(hei/10)-100)
            canvas.drawBitmap(image, x - 100, y, paint);
        }
           else
               canvas.drawBitmap(image, x , y , paint);

    }
}
