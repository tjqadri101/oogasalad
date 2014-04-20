package behaviors;

import java.util.List;

import objects.GameObject;
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
    	System.out.println("level: "+myEngine.getCurrentLevelID() + " Scene: "+myEngine.getCurrentSceneID());
    	GameObject object = myEngine.getGame().getNonPlayer(myEngine.getCurrentLevelID(), myEngine.getCurrentSceneID(), id);
    	System.out.println(myEngine.getGame().getPlayer(Game.NONUSE_ID).getAttributes());
    	if(object == null){
    		System.out.println("null object!!!");
    		return false;
    	}
    	System.out.println(object.getAttributes());
    	return myEngine.getGame().getPlayer(Game.NONUSE_ID).isAlive() && !object.isAlive();
    }

}
