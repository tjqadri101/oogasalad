package behaviors;

import java.util.List;

import objects.GameObject;

public class Eliminate extends Collision{

	public Eliminate(GameObject o) {
		super(o);
	}

	@Override
	public void collide(List<Object> objects) {
		myObject.die(); 
	}
}
