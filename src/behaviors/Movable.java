package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class Movable {
	protected GameObject myObject; 
	
	protected Movable(GameObject o){
		myObject = o;
	}
	
	public abstract void move(List<Object> objects);
}
