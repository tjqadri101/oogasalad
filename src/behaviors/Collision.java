package behaviors;

import java.util.List;

import objects.GameObject;

public abstract class Collision {
	protected GameObject myObject; 
	
	public Collision(GameObject o){
		myObject = o;
	}
	
	public abstract void collide(List<Object> objects);
}
