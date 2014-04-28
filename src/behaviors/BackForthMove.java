package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
import util.SaladUtil;
/**
 * @param double amplitude of the moving pattern
 * @param int latency; larger latency leads to slower speed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class BackForthMove extends Movable{

	public BackForthMove(GameObject o) {
		super(o);
	}

	/**
	 * @param double amplitude, int latency
	 */
	public void move(List<Object> objects) {
		double amplitude = (Double) objects.get(0);
		int latency = (Integer) objects.get(1);
		GameEngine engine = (GameEngine) myObject.eng;
		myObject.x = myObject.x + amplitude * Math.sin(engine.timer/latency);
	}

}
