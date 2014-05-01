package behaviors;

/**
 * @author Steve (Siyang) Wang
 */
import java.util.List;
import engine.GameEngine;

public class EventLevelDone extends Eventable{
    protected GameEngine myEngine;

    public EventLevelDone (GameEngine engine) {
        super(engine);
        myEngine = engine;
    }

    @Override
    public void doEvent (List<Object> params) {
    	System.out.println("EventLevelDone");
           myEngine.levelDone();
    }
}
