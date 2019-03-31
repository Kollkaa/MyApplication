package com.example.minigames.SpaceWar;

import android.content.Context;

import com.example.minigames.GameFirstExrActivity;
import com.example.minigames.R;

public class Ship extends SpaceBody {

    public Ship(Context context,int wid,int hei) {
        this.wid=wid;
        this.hei=hei;
        bitmapId = R.drawable.player; // определяем начальные параметры
        size = 10;
        x=wid/2;
        y=hei-(hei/7)-100 ;
        speed = 20;

        init(context); // инициализируем корабль
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(GameFirstExrActivity.mouseClick  ){
            if(GameFirstExrActivity.mousex>0&&GameFirstExrActivity.mousex<wid/2) {
                x = x-speed < 0? 0 : x-speed;

            }
            if(GameFirstExrActivity.mousex>=wid/2&&GameFirstExrActivity.mousex<=wid) {
                x = x+speed > wid? wid-size : x+speed;

            }

        }

    }
}
