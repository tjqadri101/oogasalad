package behaviors;

import java.util.List;

import objects.GameObject;

public abstract class Movable {
	protected GameObject myObject; 
	
	public Movable(GameObject o){
		myObject = o;
	}
	
	public abstract void move(List<Object> objects);
}
