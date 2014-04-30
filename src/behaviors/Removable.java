package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class Removable {
	protected GameObject myObject; 
	
	protected Removable(GameObject o){
		myObject = o;
	}
	
	public abstract void die(List<Object> params);
}
