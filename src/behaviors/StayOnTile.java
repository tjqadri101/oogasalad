package behaviors;

import objects.GameObject;

public class StayOnTile extends TileCollision {

	public StayOnTile(GameObject o) {
		super(o);
	}

	/**
	 * 
	 * @param tilecid
	 * @param tx
	 * @param ty
	 * @param txsize
	 * @param tysize
	 */
	@Override
	public void collide(Object ... args) {
		myObject.setSpeed(0);
		myObject.setPos(myObject.getLastX(), myObject.getLastY());
	}

}
