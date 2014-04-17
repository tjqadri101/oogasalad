package behaviors;

import objects.GameObject;

public class StayOnTile extends TileCollision {

	public StayOnTile(GameObject o) {
		super(o);
	}

	@Override
	public void collide(int tilecid, int tx, int ty, int txsize, int tysize) {
		myObject.setSpeed(0);
		myObject.setPos(myObject.x, myObject.getLastY());
	}

}
