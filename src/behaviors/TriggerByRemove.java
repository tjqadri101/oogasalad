package behaviors;

import java.util.List;

import objects.GameObject;
import objects.NonPlayer;
import stage.Game;
import engine.GameEngine;
/**
 * @param int object's ID
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class TriggerByRemove extends Triggerable{

	public TriggerByRemove(GameEngine engine) {
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
