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
		myObject.getScoreManager().update(SaladConstants.TILE_COLLISION, 
				myObject, tilecid);
		//object has an instance of TEM, so that they can call TEM if collide
		//alternative: collision has engine
		myObject.getTEManager().updateCollision(SaladConstants.COLLISION, myObject, tilecid);
		
		myObject.getBloodManager().update(SaladConstants.TILE_COLLISION, 
				myObject, tilecid);
		myObject.getLiveManager().update(SaladConstants.TILE_COLLISION, 
				myObject, tilecid);
	}
	
	public abstract void collide(List<Object> objects);
}
