package com.example.minigames.AdventureWorld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;


import com.example.minigames.R;

public class Castle extends GameObject {
    private GameSurface gameSurface;
    private int wid;
    private int hei;

    public Castle(GameSurface gameSurface,Context context, int x, int y) {
        super(x/11,y/9, 0, 100);

        this.gameSurface= gameSurface;
        init(context);
    }
    public void init(Context context) { // сжимаем картинку до нужных размеров
        Log.d("wid",""+wid/10);
        Log.d("hei",""+hei/10);
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.castle);
        this.image = Bitmap.createScaledBitmap(
                cBitmap, width, height, false);
        cBitmap.recycle();
    }

    public void draw(Canvas canvas, Paint paint)  {
        canvas.drawBitmap(image, x , y , paint);
    }


}
