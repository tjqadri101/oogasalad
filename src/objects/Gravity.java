package objects;

import saladConstants.SaladConstants;
import util.AttributeMaker;
/**
 * Manage the Gravity of the Game
 * @author Main Justin (Zihao) Zhang
 *
 */
public class Gravity {
	
	protected double myMagnitude;
	
	public Gravity(){
		myMagnitude = SaladConstants.DEFAULT_GRAVITY_MAGNITUDE;
	}
	
	/**
	 * Set the magnitude of gravity
	 * @param magnitude
	 */
	public void setMagnitude(double magnitude){
		myMagnitude = magnitude;
	}
	
	/**
	 * Get the attribute of gravity
	 * @return String Attribute
	 */
	public String getAttributes(){
		return AttributeMaker.addAttribute(SaladConstants.MODIFY_GRAVITY, 
				SaladConstants.GRAVITY_MAGNITUDE, myMagnitude);
	}
	
	/**
	 * Apply gravity to an object
	 * @param GameObject object
	 */
	public void applyGravity(GameObject object){
		if (object == null) return;
		if(object.getName() == SaladConstants.SHOOT_NAME) return;
		if(object.ydir < 0){
			object.yspeed -= myMagnitude;
		}
		else if (object.ydir > 0){
			object.yspeed += myMagnitude;	
		}
	}
	
	public double getMagnitude(){
	    return myMagnitude;
	}
}
