package ADD;

import java.util.LinkedList;
import java.util.List;

public class Main {
    private List<MyEventListner> myEventListners =new LinkedList<>();
  public void addEvenListner(MyEventListner myEventListner)
  { myEventListners.add(myEventListner);}
  public void notifyEvenListner(isColision isColision)
  {
      for (MyEventListner myevent: myEventListners)
      {myevent.processEvent(isColision);}
  }
    public static void  main(String...args){
Main main=new Main();
main.addEvenListner(new MyEventListner() {
    @Override
    public void processEvent(isColision event) {
        if(event.getSource()==null||event.getType()==null)
        {return;}
        switch (event.getType())
        {
            case ShipColision:
                System.out.println("[FIRST LISTNER] i see event type 1");

                break;
            case RocketColision:
                System.out.println("[FIRST LISTNER] i see event type 2");

                break;

                default:
                    break;
        }
    }
});
main.notifyEvenListner(new isColision(main,isColision.Type.RocketColision));
    }
}
