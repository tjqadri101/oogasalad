package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * No parameters needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class Eliminate extends Collision{

	public Eliminate(GameObject o) {
		super(o);
	}

	@Override
	public void collide(List<Object> objects) {
		System.out.println("Eliminate called ");
		GameObject hitter = (GameObject) objects.get(0);
		updateManagers(hitter);
		myObject.die(); 
	}
}
