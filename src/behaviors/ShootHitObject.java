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
public class ShootHitObject extends Collision{

	public ShootHitObject(GameObject o) {
		super(o);
	}

	@Override
	public void collide(List<Object> objects) {
		GameObject hitter = (GameObject) objects.get(0);
		updateManagers(hitter);
		hitter.doAction(SaladConstants.DIE);
		GameStats.update(SaladConstants.ENEMY_KILLED, 1);
	}
}
