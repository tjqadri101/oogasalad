package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * @param double xspeed
 * @param double yspeed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class Move extends Movable{
	
	public Move(GameObject o) {
		super(o);
	}

	@Override
	public void move(List<Object> objects) {
		double xspeed, yspeed;
		xspeed = (Double) objects.get(0);
		yspeed = (Double) objects.get(1);
		myObject.y += yspeed;
		myObject.x += xspeed;
		if(yspeed > 0){ myObject.setYHead(1); }
		else{ myObject.setYHead(-1); }
		if(xspeed > 0){ myObject.setXHead(1); }
		else{ myObject.setXHead(-1); }
	}
}
