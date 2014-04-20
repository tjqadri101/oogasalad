package behaviors;

import java.util.List;

import objects.GameObject;
import objects.NonPlayer;
import stage.Game;
import engine.GameEngine;

public class WinByCollision extends Winnable{

	public WinByCollision(GameEngine engine) {
		super(engine);
	}
	
	   
    /**
     * @param objectID
     */
    @Override
    public boolean checkGoal(List<Object> params) {
    	int id = (Integer) params.get(0);
    	NonPlayer object = myEngine.getGame().getNonPlayer(myEngine.getCurrentLevelID(), myEngine.getCurrentSceneID(), id);
    	return myEngine.getGame().getPlayer(Game.NONUSE_ID).isAlive() && !object.isAlive();
    }

}
