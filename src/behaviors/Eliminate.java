package behaviors;

import objects.GameObject;

public class Eliminate extends Collision{

	public Eliminate(GameObject o) {
		super(o);
	}

	@Override
	public void collide(GameObject hitter, GameObject victim) {
		victim.die();
	}
}
