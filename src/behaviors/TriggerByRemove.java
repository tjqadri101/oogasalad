package behaviors;

import java.util.List;

import objects.NonPlayer;
import objects.Player;
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
    	if (object==null) {return false;}
    	List<Player> players = myEngine.getGame().getAllPlayers();
    	boolean playerAlive = false;
    	for(Player p: players){
    		if(p.isAlive()) playerAlive = true;
    	}
    	return playerAlive && !object.isAlive();
    }

}
