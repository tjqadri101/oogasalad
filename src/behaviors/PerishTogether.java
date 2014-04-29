package behaviors;

import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
import statistics.GameStats;
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
		myObject.doAction(SaladConstants.DIE);
		GameObject hitter = (GameObject) objects.get(0);
		updateManagers(hitter);
		hitter.doAction(SaladConstants.DIE);
		GameStats.update(SaladConstants.ENEMY_KILLED, 1);
	}
}
