package behaviors;

import objects.GameObject;

public class PerishTogether extends Collision{

	public PerishTogether(GameObject o) {
		super(o);
	}

	/**
	 * @param GameObject Hitter
	 */
	@Override
	public void collide(Object ... args) {
		myObject.die();
		GameObject hitter = (GameObject) args[0];
		hitter.die();
	}
}
