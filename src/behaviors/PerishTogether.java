package behaviors;

import objects.GameObject;

public class PerishTogether extends Collision{

	public PerishTogether(GameObject o) {
		super(o);
	}

	@Override
	public void collide(GameObject hitter) {
		myObject.die();
		hitter.die();
	}
}
