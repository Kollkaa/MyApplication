package com.example.myapplication.AdventureWorld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.myapplication.GameObject;
import com.example.myapplication.GameSurface;
import com.example.myapplication.R;

public class Shop extends GameObject {
    private GameSurface gameSurface;

    private int wid;
    private int hei;


    public Shop(GameSurface gameSurface,Context context, int x, int y) {
    super(x/10,y/10, x-(x/10), y-(y/10));
        this.gameSurface= gameSurface;

init(context);
        }
    public void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.shop);
        image = Bitmap.createScaledBitmap(
                cBitmap, width, height, false);
        cBitmap.recycle();
    }

    public void draw(Canvas canvas, Paint paint)  {
        canvas.drawBitmap(image, x , y , paint);
    }
}
