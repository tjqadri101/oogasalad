package behaviors;

import java.util.List;

import objects.GameObject;

public class AwardBlood extends Collision{

	protected AwardBlood(GameObject o) {
		super(o);
	}

	/**
	 * @param hitter
	 * @param value
	 */
	@Override
	public void collide(List<Object> objects) {
		GameObject hitter = (GameObject) objects.get(0);
		int value = (Integer) objects.get(1);
		hitter.die();
		myObject.incrementBlood(value);
	}

}
