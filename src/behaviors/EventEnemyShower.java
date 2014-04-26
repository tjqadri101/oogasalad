package behaviors;

import java.util.List;
import engine.GameEngine;

public class EventEnemyShower extends Eventable{
    protected GameEngine myEngine;

    protected EventEnemyShower (GameEngine engine) {
        super(engine);
        myEngine = engine;
    }

    @Override
    public void doEvent (List<Object> params) {
        //
        
        if(myEngine.level>=3){
            myEngine.gameOver();
            System.out.println("lol");
    }
    else
            myEngine.levelDone();
    }
}
