package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class Collision {
	protected GameObject myObject; 
	
	protected Collision(GameObject o){
		myObject = o;
	}
	
	public abstract void collide(List<Object> objects);
}
