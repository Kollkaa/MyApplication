package com.example.myapplication.SpaceWar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SpaceBody {
    public float getX() {
        return x;
    }

    protected float x; // координаты

    public float getY() {
        return y;
    }

    protected float y;

    public float getSize() {
        return size;
    }

    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка
    protected int hp;
    public void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int) (size * GameView.unitW), (int) (size * GameView.unitH), false);
        cBitmap.recycle();
    }

  public   void update() { // тут будут вычисляться новые координаты
    }

   public void drow(Paint paint, Canvas canvas) { // рисуем картинку
        canvas.drawBitmap(bitmap, x * GameView.unitW, y * GameView.unitH, paint);
    }
}
