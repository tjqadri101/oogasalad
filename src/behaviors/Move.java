package behaviors;

import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
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
		if(yspeed > 0){ myObject.setYHead(SaladConstants.POSITIVE_DIRECTION); }
		else{ myObject.setYHead(SaladConstants.NEGATIVE_DIRECTION); }
		if(xspeed > 0){ myObject.setXHead(SaladConstants.POSITIVE_DIRECTION); }
		else{ myObject.setXHead(SaladConstants.NEGATIVE_DIRECTION); }
	}
}

