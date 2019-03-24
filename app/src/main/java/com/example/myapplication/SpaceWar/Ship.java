package com.example.myapplication.SpaceWar;

import android.content.Context;

import com.example.myapplication.GameFirstExrActivity;
import com.example.myapplication.R;

public class Ship extends SpaceBody {
    public Ship(Context context) {
        bitmapId = R.drawable.player; // определяем начальные параметры
        size = 15f;
        x=20f;
        y=GameView.maxY ;
        speed = (float) 0.4;

        init(context); // инициализируем корабль
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(GameFirstExrActivity.mouseClick  ){
            if(GameFirstExrActivity.mousex>0&&GameFirstExrActivity.mousex<950) {
                x = x <= 0 ? 0 : x-size/5;

            }
            if(GameFirstExrActivity.mousex>=550&&GameFirstExrActivity.mousex<=1920) {
                x = x > 195? 195f : x+size/5;

            }

        }

    }
}
