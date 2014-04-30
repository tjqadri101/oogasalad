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
public class Eliminate extends Collision{

	public Eliminate(GameObject o) {
		super(o);
	}

	@Override
	public void collide(List<Object> objects) {
		GameObject hitter = (GameObject) objects.get(0);
		updateManagers(hitter);
		myObject.doAction(SaladConstants.DIE); 
		GameStats.update(SaladConstants.ENEMY_KILLED, 1);
	}
}
