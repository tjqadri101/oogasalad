package behaviors;

import java.util.List;

import objects.GameObject;

public abstract class Shootable {
	
	protected GameObject myObject;
	
	protected Shootable(GameObject o){
		myObject = o;
	}
	
	public abstract void shoot(List<Object> objects);
}
