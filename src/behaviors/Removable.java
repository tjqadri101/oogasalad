package behaviors;

import java.util.List;

import objects.GameObject;

public abstract class Removable {
	protected GameObject myObject; 
	
	protected Removable(GameObject o){
		myObject = o;
	}
	
	public abstract void remove(List<Object> params);
}
