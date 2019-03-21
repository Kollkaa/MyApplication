package com.example.myapplication;
import android.graphics.Bitmap;

public abstract class GameObject {

    protected Bitmap image;

    protected int rowCount;
    protected int colCount;

    protected int WIDTH;
    protected int HEIGHT;

    protected int width;


    protected int height;
    protected int x;
    protected int y;



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

    public GameObject() {
    }

    public GameObject(Bitmap image, int width,int height,int x, int y,int e) {


        this.width = width;
        this.height= height;
        this.image = image;
        this.x= x;
        this.y= y;

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
