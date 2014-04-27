package behaviors;

import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
/**
 * No parameters needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class Eliminate extends Collision{

	public Eliminate(GameObject o) {
		super(o);
	}

	@Override
	public void collide(List<Object> objects) {
		GameObject hitter = (GameObject) objects.get(0);
		myObject.getScoreManager().update(SaladConstants.COLLISION, 
				myObject, hitter);
		//object has an instance of TEM, so that they can call TEM if collide
		//alternative: collision has engine
		
		
		myObject.getBloodManager().update(SaladConstants.COLLISION, 
				myObject, hitter);
		myObject.die(); 
	}
}
