package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
/**
 * @param double amplitude of the moving pattern
 * @param int latency; larger latency leads to slower speed
 * @param double yspeed
 * @author Main Justin (Zihao) Zhang
 */
public class BackForthMoveWithVerticalSpeed extends Movable{

	public BackForthMoveWithVerticalSpeed(GameObject o) {
		super(o);
	}

	/**
	 * @param double amplitude
	 * @param int latency
	 * @param double yspeed
	 */
	public void move(List<Object> objects) {
		double amplitude = (Double) objects.get(0);
		int latency = (Integer) objects.get(1);
		double yspeed = (Double) objects.get(2);
		GameEngine engine = (GameEngine) myObject.eng;
		double diff = Math.sin(engine.getSaladTimer()/latency);
		myObject.x = myObject.x + amplitude * diff;
		myObject.y += yspeed;
		if(diff >= 0) myObject.setXHead(1);
		else myObject.setXHead(-1);
		myObject.setYHead(1);
	}

}
