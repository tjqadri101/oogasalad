package behaviors;

import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class Collision {
	protected GameObject myObject; 
	
	protected Collision(GameObject o){
		myObject = o;
	}
	
	protected void updateManagers(GameObject hitter){
		System.out.println("updateManagers called ");
		myObject.getScoreManager().update(SaladConstants.COLLISION, 
				myObject, hitter);
		
		//object has an instance of TEM, so that they can call TEM if collide
		//alternative: collision has engine
		myObject.getTEManager().updateCollision(SaladConstants.COLLISION, myObject, hitter);
		
		myObject.getBloodManager().update(SaladConstants.COLLISION, 
				myObject, hitter);
		myObject.getLiveManager().update(SaladConstants.COLLISION, 
				myObject, hitter);
	}
	
	public abstract void collide(List<Object> objects);
}
