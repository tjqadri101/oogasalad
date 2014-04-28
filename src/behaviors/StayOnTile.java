package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * No parameter needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class StayOnTile extends TileCollision {

	public StayOnTile(GameObject o) {
		super(o);
	}

	/**
	 * @param tilecid
	 * @param tx
	 * @param ty
	 * @param txsize
	 * @param tysize
	 */
	@Override
	public void collide(List<Object> objects) {
		myObject.ground();
		System.out.println("StayOnTile: " + objects.get(0));
		System.out.println("StayOnTile: " + objects.get(1));
		updateManagers((Integer) objects.get(0));
	}

}
