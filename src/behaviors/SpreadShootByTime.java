package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
/**
 * Shoot a line of bullets and spread out
 * @author Main Justin (Zihao) Zhang
 */
public class SpreadShootByTime extends Shootable{

	public SpreadShootByTime(GameObject o) {
		super(o);
	}

	/**
	 * @param String bullet's Image Name
	 * @param int x size of the image
	 * @param int y size of the image
	 * @param int collision ID of the bullet
	 * @param double absolute speed of the bullet
	 * @param the number of bullets per shoot
	 * @param int time latency, the larger the slower
	 */
	@Override
	public void shoot(List<Object> objects) {
		GameEngine engine = (GameEngine) myObject.eng;
		
		String imageName = (String) objects.get(0);
		int xsize = (Integer) objects.get(1);
		int ysize = (Integer) objects.get(2);
		int colid = (Integer) objects.get(3);
		double shootSpeed = (Double) objects.get(4);
		int times = (Integer) objects.get(5);
		int latency = (Integer) objects.get(5);
		
		double shootXSpeed, shootYSpeed, xpos, ypos;
		if(myObject.getXHead() < 0){ xpos = myObject.x - xsize; }
		else if (myObject.getXHead() > 0){ xpos = myObject.x + myObject.getXSize(); }
		else{ xpos = myObject.getXHead() + myObject.getXSize()/2; }
		if(myObject.getYHead() < 0){ ypos = myObject.y - ysize; }
		else if (myObject.getYHead() > 0){ ypos = myObject.y + myObject.getYSize(); }
		else{ ypos = myObject.y + myObject.getYSize()/2; }
		shootXSpeed = myObject.getXHead()*shootSpeed;
		shootYSpeed = myObject.getYHead()*shootSpeed;

		if(engine.getSaladTimer() % (engine.getGameSpeed() * latency) == 0){
			System.out.println("Shoot!!");
			for(int i = 0; i < times; i ++){
				NonPlayer object = engine.createActor(SaladConstants.NULL_UNIQUE_ID, imageName, xsize, ysize, xpos, ypos, SaladConstants.SHOOT_NAME, colid, SaladConstants.SHOOT_LIVES);
				object.expiry = object.expire_off_view;
				if(myObject.getXHead() == 0){ object.setSpeed(shootSpeed*(-1.0*times/2 + i), shootYSpeed); }
				else{ object.setSpeed(shootXSpeed, shootSpeed*(-times/2 + i)); }	
				object.setDieBehavior(SaladConstants.REGULAR_REMOVE);
			}
		}
	}

}