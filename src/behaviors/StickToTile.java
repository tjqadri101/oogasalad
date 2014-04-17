package behaviors;

import objects.GameObject;

public class StickToTile extends TileCollision {

	public StickToTile(GameObject o) {
		super(o);
	}

	@Override
	public void collide(int tilecid, int tx, int ty, int txsize, int tysize) {
//		myObject.setSpeed(0);
		myObject.setDir(0, 0);
		myObject.setIsAir(false);
		myObject.setPos(myObject.getLastX(), myObject.getLastY());
	}

}
