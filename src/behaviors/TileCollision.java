package behaviors;

import objects.GameObject;

public abstract class TileCollision {
	protected GameObject myObject; 
	
	public TileCollision(GameObject o){
		myObject = o;
	}
	
	public abstract void collide(int tilecid, int tx, int ty, int txsize, int tysize);
}
