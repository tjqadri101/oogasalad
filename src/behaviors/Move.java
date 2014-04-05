package behaviors;

import objects.GameObject;

public class Move extends Movable{
	
	public Move(GameObject o) {
		super(o);
	}

	@Override
	public void move(double xspeed, double yspeed) {
		myObject.setSpeed(xspeed, yspeed);
	}
}
