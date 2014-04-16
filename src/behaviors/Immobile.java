package behaviors;

import objects.GameObject;

public class Immobile extends Movable {

	public Immobile(GameObject o) {
		super(o);
	}

	@Override
	public void move(double xspeed, double yspeed) {
		// do nothing
	}
}
