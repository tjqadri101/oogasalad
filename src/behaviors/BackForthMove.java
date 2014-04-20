package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;

public class BackForthMove extends Movable{
//	public static final int TIME_BUFFER = 10;

	public BackForthMove(GameObject o) {
		super(o);
	}

	/**
	 * @param double amplitude, int latency
	 */
	public void move(List<Object> objects) {
		double amplitude = (Double) objects.get(0);
		int Latency = (Integer) objects.get(1);
		GameEngine engine = (GameEngine) myObject.eng;
		myObject.x = myObject.x + amplitude * Math.sin(engine.timer/Latency);
	}

}
