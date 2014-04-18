package behaviors;

import objects.GameObject;

public class Move extends Movable{
	
	public Move(GameObject o) {
		super(o);
	}

	@Override
	public void move(Object ... args) {
		double xspeed, yspeed;
		xspeed = (Double) args[0];
		yspeed = (Double) args[1];
		myObject.setSpeed(xspeed, yspeed);
	}
}
