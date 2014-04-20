package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * No parameters needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class Immobile extends Movable {

	public Immobile(GameObject o) {
		super(o);
	}

	@Override
	public void move(List<Object> objects) {
		// do nothing
	}
}
