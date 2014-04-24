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
		myObject.getScoreManager().updateScore(SaladConstants.HITTER_ELIMINATE_VICTIM, 
				myObject.colid, hitter.colid);
		myObject.die(); 
	}
}
