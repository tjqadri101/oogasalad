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
    	System.out.println("checkGoal id: " + id);
    	System.out.println("checkGoal level: "+myEngine.getCurrentLevelID() + " Scene: "+myEngine.getCurrentSceneID());
    	NonPlayer object = myEngine.getGame().getNonPlayer(myEngine.getCurrentLevelID(), myEngine.getCurrentSceneID(), id);
    	if(object == null){
    		System.out.println("null object!!!");
    		return false;
    	}
    	System.out.println(object.getAttributes());
    	return myEngine.getGame().getPlayer(Game.NONUSE_ID).isAlive() && !object.isAlive();
    }

}
