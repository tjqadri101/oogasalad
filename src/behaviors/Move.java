package behaviors;

import java.util.List;

import engine.GameEngine;
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
		GameEngine engine = (GameEngine) myObject.eng;
		xspeed = (Double) objects.get(0);
		yspeed = (Double) objects.get(1);
		myObject.y += yspeed;
		myObject.x += xspeed;
	}
}
