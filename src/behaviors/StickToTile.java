package behaviors;

import objects.GameObject;

public class StickToTile extends TileCollision {

	public StickToTile(GameObject o) {
		super(o);
	}

	@Override
	public void collide(int tilecid, int tx, int ty, int txsize, int tysize) {
		System.out.println(myObject.x+" "+myObject.y);
		myObject.setPos(myObject.getLastX(), myObject.getLastY());
	}

}
