package FlipsBird;

import android.content.Context;

import com.example.minigames.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ADD.MyEventListner;
import ADD.isColision;

public class Rectangel extends Object_Flip {
    private List<MyEventListner> myEventListners =new LinkedList<>();
    public Rectangel(Context context, int complexity, int wid, int hei) {
        hp=1;
        this.wid=wid;
        this.hei=hei;
        Random random = new Random();
        int re=random.nextInt(3);
        speed=10;
        size=10;
            bitmapId=R.drawable.Ractangle;
        y=0;
        x = wid-(wid/size);
        int r=random.nextInt(5);



        init(context);
    }








    public boolean isCollision(Bird bird) {
        return !(((x+bitmap.getWidth()) < bird.getX())||(x > (bird.getX()+bird.bitmap.getWidth()))||((y+bitmap.getHeight()) < bird.getY())||(y > (bird.getY()+bird.bitmap.getHeight())));
    }
    public void addEvenListner(MyEventListner myEventListner)
    { myEventListners.add(myEventListner);}
    public void notifyEvenListner(isColision isColision)
    {
        for (MyEventListner myevent: myEventListners)
        {myevent.processEvent(isColision);}
    }
}
