package behaviors;

import objects.GameObject;

public abstract class Collision {
	protected GameObject myObject; 
	
	public Collision(GameObject o){
		myObject = o;
	}
	
	public abstract void collide(Object ... args);
}
