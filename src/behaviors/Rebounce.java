package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * Only the hitter rebounces while hitting the victim
 * The hitter cannot be a player
 * @author Main Justin (Zihao) Zhang
 */
public class Rebounce extends Collision{

	public Rebounce(GameObject o) {
		super(o);
	}

	@Override
	public void collide(List<Object> objects) {
		GameObject hitter = (GameObject) objects.get(0);
		updateManagers(hitter);
		hitter.bounce();
	}
}
