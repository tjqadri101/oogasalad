package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class TileCollision {
	protected GameObject myObject; 
	
	protected TileCollision(GameObject o){
		myObject = o;
	}
	
	public abstract void collide(List<Object> objects);
}
