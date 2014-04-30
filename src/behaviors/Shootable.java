package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class Shootable {
	
	protected GameObject myObject;
	
	protected Shootable(GameObject o){
		myObject = o;
	}
	
	protected double[] locateShootLocation(int xsize, int ysize, double shootSpeed) {
		double shootXSpeed, shootYSpeed, xpos, ypos;
		if(myObject.getXHead() < 0){ xpos = myObject.x - xsize; }
		else if (myObject.getXHead() > 0){ xpos = myObject.x + myObject.getXSize(); }
		else{ xpos = myObject.x + myObject.getXSize()/2; }
		if(myObject.getYHead() < 0){ ypos = myObject.y - ysize; }
		else if (myObject.getYHead() > 0){ ypos = myObject.y + myObject.getYSize(); }
		else{ ypos = myObject.y + myObject.getYSize()/2; }
		shootXSpeed = myObject.getXHead()*shootSpeed;
		shootYSpeed = myObject.getYHead()*shootSpeed;
		double[] answer = {xpos, ypos, shootXSpeed, shootYSpeed};
		return answer;
	}
	
	protected void createShootThing(GameEngine engine, String imageName, int xsize, int ysize,
			double xpos, double ypos, int colid, double xspeed, double yspeed){
		NonPlayer object = engine.createActor(SaladConstants.NULL_UNIQUE_ID, imageName, xsize, ysize, 
				xpos, ypos, SaladConstants.SHOOT_NAME, 
				colid, SaladConstants.SHOOT_LIVES);
		object.expiry = object.expire_off_view;
		object.setBehavior(SaladConstants.REGULAR_MOVE, xspeed, yspeed);
		object.setBehavior(SaladConstants.REGULAR_REMOVE);
		myObject.addShotThing(object);
	}
	
	public abstract void shoot(List<Object> objects);
}
