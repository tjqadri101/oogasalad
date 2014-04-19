package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;

public class BackForthMove extends Movable{

	public BackForthMove(GameObject o) {
		super(o);
	}

	/**
	 * @param double amplitude, double maxXSpeed, double maxYSpeed
	 */
	public void move(List<Object> objects) {
		double amplitude = (Double) objects.get(0);
		GameEngine engine = (GameEngine) myObject.eng;
		myObject.x = myObject.x + amplitude * Math.sin(engine.timer);
	}

}
