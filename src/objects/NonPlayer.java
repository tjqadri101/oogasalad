package objects;

import java.util.ArrayList;
import java.util.List;

import saladConstants.SaladConstants;
import engineManagers.BloodManager;
import engineManagers.CollisionManager;
import engineManagers.LiveManager;
import engineManagers.RevivalManager;
import engineManagers.ScoreManager;
import engineManagers.TriggerEventManager;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */

public class NonPlayer extends GameObject {
	
	private List<String> myActions;

	public NonPlayer(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, 
			String name, int collisionId, int lives, 
			CollisionManager collisionManager, ScoreManager scoreManager, BloodManager bloodManager, 
			RevivalManager revivalManager, LiveManager liveManager, TriggerEventManager triggerEventManager) {
		
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives, collisionManager, 
				scoreManager, bloodManager, revivalManager, liveManager, triggerEventManager);
		
		myActions = new ArrayList<String>();
		myActions.add(SaladConstants.MOVE);
		myActions.add(SaladConstants.JUMP);
		myActions.add(SaladConstants.SHOOT);
	}
	
	/**
	 * Add an action that will be checked and executed if already set for each frame
	 * Applied to both live editing and play mode
	 * @param action type (i.e. move, jump, shoot)
	 */
	public void addDoFrameAction(String type){
		myActions.add(type);
	}
	
	/**
	 * Delete an action that was checked and executed for each frame in live editing or play mode
	 * @param action type (i.e. move, jump, shoot)
	 */
	public void deleteDoFrameAction(String type){
		myActions.remove(type);
	}
	
	@Override
	public void move(){
		super.move();
		for(String action: myActions){
			doAction(action);
		}
	}
}
