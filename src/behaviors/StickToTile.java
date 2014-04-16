package behaviors;

import objects.GameObject;

public class StickToTile extends TileCollision {

	public StickToTile(GameObject o) {
		super(o);
	}

	@Override
	public void collide(int tilecid, int tx, int ty, int txsize, int tysize) {
		myObject.setSpeed(0);
		myObject.in_the_air = false;
		myObject.setPos(myObject.getLastX(), myObject.getLastY());
	}

}
