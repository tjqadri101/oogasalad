package behaviors;

import java.util.List;

import objects.NonPlayer;
import stage.Game;
import engine.GameEngine;

/**
* @author Main Steve (Siyang) Wang
*/
public class TriggerByCollision extends Triggerable {
    public TriggerByCollision(GameEngine engine) {
        super(engine);
    }

    /**
     * @param objectID
     */
    @Override
    public boolean checkTrigger(List<Object> params) {
        int id = (Integer) params.get(0);
        NonPlayer object = myEngine.getGame().getNonPlayer(myEngine.getCurrentLevelID(), myEngine.getCurrentSceneID(), id);
        return myEngine.getGame().getPlayer(Game.NONUSE_ID).isAlive() && !object.isAlive();
    }

}
