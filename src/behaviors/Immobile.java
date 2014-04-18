package behaviors;

import objects.GameObject;

public class Immobile extends Movable {

	public Immobile(GameObject o) {
		super(o);
	}

	@Override
	public void move(Object ... args) {
		// do nothing
	}
}
