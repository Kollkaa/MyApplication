package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Shop extends GameObject {
    private GameSurface gameSurface;
    public Shop() {
    }

    public Shop(GameSurface gameSurface,Bitmap image, int x, int y) {
    super(image,50,50, x, y,0);
        this.gameSurface= gameSurface;

        }
    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.image;
        canvas.drawBitmap(bitmap,x, y, null);
        // Last draw time.
    }
}
