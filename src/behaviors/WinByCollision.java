package behaviors;

import java.util.List;

import objects.GameObject;
import engine.GameEngine;

public class WinByCollision extends Winnable{

	protected WinByCollision(GameEngine engine) {
		super(engine);
	}
	
	   
    /**
     * @param objectID
     */
    @Override
    public boolean checkGoal(List<Object> params) {
    	int id = (Integer) params.get(0);
    	GameObject object = myEngine.getGame().getNonPlayer(myEngine.getCurrentLevelID(), myEngine.getCurrentSceneID(), id);
    	return !object.isAlive();
    	//can either check hit, or check the life value of the player. Latter is better, but how to 
    	//decrement when the NonPlayer is hitted?*/
    }

}
