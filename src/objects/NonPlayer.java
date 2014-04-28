package objects;

//import engineManagers.AnimationManager;
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

	
	public NonPlayer(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, 
			String name, int collisionId, int lives, 
			CollisionManager collisionManager, ScoreManager scoreManager, BloodManager bloodManager, 
			RevivalManager revivalManager, LiveManager liveManager, TriggerEventManager triggerEventManager) {
		
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives, collisionManager, 
				scoreManager, bloodManager, revivalManager, liveManager, triggerEventManager);
	
		colid = collisionId;
	}
	
	public int getColID(){
		return colid; 
	}
	
	
	
	

	@Override
	public void move(){
		super.move();
		autoMove();
//		if(getID() == -1){
//			System.out.println(getImageName());
//		}
	}
}
