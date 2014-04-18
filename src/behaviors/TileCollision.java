package behaviors;

import objects.GameObject;

public abstract class TileCollision {
	protected GameObject myObject; 
	
	public TileCollision(GameObject o){
		myObject = o;
	}
	
	public abstract void collide(Object ... args);
}
