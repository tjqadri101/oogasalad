package behaviors;

/**
 * @author Steve (Siyang) Wang
 */
import java.util.List;
import engine.GameEngine;

public class EventLevelDone extends Eventable{
    protected GameEngine myEngine;

    protected EventLevelDone (GameEngine engine) {
        super(engine);
        myEngine = engine;
    }

    @Override
    public void doEvent (List<Object> params) {
        //Consider changing the input parameter
        if(myEngine.level>=3){
            myEngine.gameOver();
            System.out.println("lol");
    }
    else
            myEngine.levelDone();
    }
}
