package com.example.myapplication;

import android.content.Context;

class Ship extends SpaceBody {
    public Ship(Context context) {
        bitmapId = R.drawable.ship; // определяем начальные параметры
        size = 5;
        x=7;
        y=GameView.maxY - size+1;
        speed = (float) 0.4;

        init(context); // инициализируем корабль
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(GameFirstExrActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(GameFirstExrActivity.isRightPressed && x <= GameView.maxX - 4){
            x += speed;
        }
    }
}
