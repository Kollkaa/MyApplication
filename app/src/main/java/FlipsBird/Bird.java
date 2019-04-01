package FlipsBird;

import android.content.Context;

import com.example.minigames.FlipActivity;
import com.example.minigames.GameFirstExrActivity;
import com.example.minigames.R;

import java.util.LinkedList;
import java.util.List;

import ADD.MyEventListner;
import ADD.isColision;

public class Bird extends Object_Flip
{       int jump_strong;
        private List<MyEventListner> myEventListners =new LinkedList<>();
        public Bird(Context context, int wid, int hei) {
            this.wid=wid;
            this.hei=hei;
            bitmapId = R.drawable.player; // определяем начальные параметры
            size = 10;
            x=100;
            y=hei/2 ;
            jump_strong=10;

            init(context); // инициализируем корабль
        }

        @Override
        public void update() { // перемещаем корабль в зависимости от нажатой кнопки
            if(GameFirstExrActivity.mouseClick  ){
                if(FlipActivity.clickup) {
                    y = y-jump_strong <0?0:y-jump_strong;
                    FlipActivity.clickup=false;
                }
                if(!FlipActivity.clickup) {
                    y = y+jump_strong >hei-(hei/size)?y+jump_strong:(hei-hei/size);

                }

            }

        }


    public void addEvenListner(MyEventListner myEventListner)
    { myEventListners.add(myEventListner);}
    public void notifyEvenListner(isColision isColision)
    {
        for (MyEventListner myevent: myEventListners)
        {myevent.processEvent(isColision);}
    }
}
