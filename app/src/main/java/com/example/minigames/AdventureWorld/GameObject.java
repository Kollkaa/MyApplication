package com.example.minigames.AdventureWorld;
import android.graphics.Bitmap;
import android.graphics.Paint;

public abstract class GameObject {

    protected Bitmap image;

    private Paint paint;
    protected final int rowCount;
    protected final int colCount;

    protected final int WIDTH;
    protected final int HEIGHT;

    protected final int width;


    protected final int height;
    protected  int x;
    protected  int y;



    public GameObject(Bitmap image, int rowCount, int colCount, int x, int y)  {

        this.image = image;
        this.rowCount= rowCount;
        this.colCount= colCount;

        this.x= x;
        this.y= y;

        this.WIDTH = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.width = this.WIDTH/ colCount;
        this.height= this.HEIGHT/ rowCount;
    }
    public GameObject( int height, int width, int x, int y)  {

if (x<0)
    x=0;
            if(y<0)
                y=0;
        this.rowCount= 0;
        this.colCount= 0;

        this.x= x;
        this.y= y;

        this.WIDTH =height;
        this.HEIGHT =width;

        this.width = this.WIDTH;
        this.height= this.HEIGHT;
    }



    protected Bitmap createSubImageAt(int row, int col)  {
        // createBitmap(bitmap, x, y, width, height).
        Bitmap subImage = Bitmap.createBitmap(image, col* width, row* height ,width,height);
        return subImage;
    }

    public int getX()  {
        return this.x;
    }

    public int getY()  {
        return this.y;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
