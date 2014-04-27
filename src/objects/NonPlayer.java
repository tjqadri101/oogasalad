package objects;

//import engineManagers.AnimationManager;
import engineManagers.BloodManager;
import engineManagers.CollisionManager;
import engineManagers.RevivalManager;
import engineManagers.ScoreManager;
import saladConstants.SaladConstants;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public class NonPlayer extends GameObject {

	public NonPlayer(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, 
			String name, int collisionId, int lives, 
			CollisionManager collisionManager, ScoreManager scoreManager, BloodManager bloodManager, RevivalManager revivalManager) {
		
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives, collisionManager, scoreManager, bloodManager, revivalManager);
		myIsPlayer = false;
		myAttributes.add(SaladConstants.CREATE_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + 
				SaladConstants.IMAGE + "," + getGraphic() + "," + myXSize + "," + myYSize + "," +
				SaladConstants.POSITION + "," + myInitX + "," + myInitY + "," + SaladConstants.NAME + "," + getName() + "," + 
				SaladConstants.COLLISION_ID + "," + colid + "," + SaladConstants.LIVES + "," + myInitBlood);
	}

	@Override
	public void move(){
		super.move();
		autoMove();
	}
}
