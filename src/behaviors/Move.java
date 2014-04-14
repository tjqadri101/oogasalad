package behaviors;

import org.jbox2d.common.Vec2;

import objects.GameObject;

public class Move extends Movable{
	
	public Move(GameObject o) {
		super(o);
	}

	@Override
	public void move(double xspeed, double yspeed) {
		System.out.println("move called: " + xspeed + " " + yspeed);
		Vec2 velocity = myObject.getBody().getLinearVelocity();
		System.out.println("current velocity: " + velocity.x + " " + velocity.y);
   		velocity.y = (float) yspeed;
   		velocity.x = (float) xspeed;
   		myObject.getBody().setLinearVelocity(velocity);
//		myObject.setSpeed(xspeed, yspeed);
	}
}
