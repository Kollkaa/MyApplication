package ADD;

import java.util.EventObject;

public class isColision extends EventObject {
      /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */

    private Type type;
    public isColision(Object source,Type type) {
                super(source);
    this.type=type;
    }
    public enum Type{ShipColision,RocketColision,AsteroidColision,RocketKillAsteroid,ChibiKilled,AllChebiKilled}

    public Type getType() {  return type;   }

    public void setType(Type type) {  this.type = type;  }
}
