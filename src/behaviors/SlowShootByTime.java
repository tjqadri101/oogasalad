package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
/**
 * @param String bullet's Image Name
 * @param int x size of the image
 * @param int y size of the image
 * @param int collision ID of the bullet
 * @param double absolute speed of the bullet
 * @param int time latency, the larger the slower
 * @author Main Justin (Zihao) Zhang
 */
public class SlowShootByTime extends Shootable{

	public SlowShootByTime(GameObject o) {
		super(o);
	}

	@Override
	public void shoot(List<Object> objects) {
		GameEngine engine = (GameEngine) myObject.eng;
		
		String imageName = (String) objects.get(0);
		int xsize = (Integer) objects.get(1);
		int ysize = (Integer) objects.get(2);
		int colid = (Integer) objects.get(3);
		double shootSpeed = (Double) objects.get(4);
		int latency = (Integer) objects.get(5);
		
		double[] property = locateShootLocation(xsize, ysize, shootSpeed);
		
		if(engine.getSaladTimer() % (engine.getGameSpeed() * latency) == 0){
			NonPlayer object = engine.createActor(SaladConstants.NULL_UNIQUE_ID, imageName, xsize, ysize, property[0], property[1], SaladConstants.SHOOT_NAME, colid, SaladConstants.SHOOT_LIVES);
			object.expiry = object.expire_off_view;
			object.setSpeed(property[2], property[3]);
			object.setDieBehavior(SaladConstants.REGULAR_REMOVE);	
		}
	}

}