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
		myObject.updateManagers(SaladConstants.COLLISION, myObject, hitter);
		hitter.updateManagers(SaladConstants.COLLISION, hitter, myObject);
	}
	
	public abstract void collide(List<Object> objects);
}
