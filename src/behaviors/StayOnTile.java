package behaviors;

import objects.GameObject;

public class StayOnTile extends TileCollision {

	public StayOnTile(GameObject o) {
		super(o);
	}

	@Override
	public void collide(int tilecid, int tx, int ty, int txsize, int tysize) {
		if(myObject.y < ty) myObject.setDir(myObject.xdir, 0);
		myObject.setIsAir(false);
		myObject.setPos(myObject.getLastX(), myObject.getLastY());
	}

}
