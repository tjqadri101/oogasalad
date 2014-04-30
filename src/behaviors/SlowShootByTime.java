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
 * @param int max number of bullets allowed on the screen
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
		int numBullets = (Integer) objects.get(6);
		if(myObject.getNumAliveShots() >= numBullets) return;
		
		double[] property = locateShootLocation(xsize, ysize, shootSpeed);
		
		if((engine.getSaladTimer() + latency) % (latency) == 0){
			createShootThing(engine, imageName, xsize, ysize, property[0], property[1], colid,
					property[2], property[3]);	
		}
	}

}