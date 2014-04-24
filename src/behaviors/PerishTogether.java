package behaviors;

import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
/**
 * No parameters needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class PerishTogether extends Collision{

	public PerishTogether(GameObject o) {
		super(o);
	}

	/**
	 * @param GameObject Hitter
	 */
	@Override
	public void collide(List<Object> objects) {
		myObject.die();
		GameObject hitter = (GameObject) objects.get(0);
		myObject.getScoreManager().updateScore(SaladConstants.PERISH_TOGETHER, 
				myObject.colid, hitter.colid);
		hitter.die();
	}
}
