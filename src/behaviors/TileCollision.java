package behaviors;

import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */

public abstract class TileCollision {
	protected GameObject myObject; 
	
	protected TileCollision(GameObject o){
		myObject = o;
	}
	
	protected void updateManagers(int tilecid){
		myObject.updateManagers(SaladConstants.TILE_COLLISION, myObject, tilecid);
	}
	
	public abstract void collide(List<Object> objects);
}
