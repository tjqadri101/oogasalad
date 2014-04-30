package objects;

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
	
	protected int myXDir;
	protected int myYDir;

	public NonPlayer(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, 
			String name, int collisionId, int lives, 
			CollisionManager collisionManager, ScoreManager scoreManager, BloodManager bloodManager, 
			RevivalManager revivalManager, LiveManager liveManager, TriggerEventManager triggerEventManager) {
		
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives, collisionManager, 
				scoreManager, bloodManager, revivalManager, liveManager, triggerEventManager);
		
		myXDir = 1;
		myYDir = 1;
	}
	
	@Override
	public void move(){
		super.move();
		doAction(SaladConstants.MOVE);
		doAction(SaladConstants.JUMP);
		doAction(SaladConstants.SHOOT);
	}
}
