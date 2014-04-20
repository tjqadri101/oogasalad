package objects;

import saladConstants.SaladConstants;

public class Gravity {
	
	protected double myMagnitude;
	
	public Gravity(){}
	
	public void setMagnitude(double magnitude){
		myMagnitude = magnitude;
	}
	
	/**
	 * 
	 * @return String Attribute
	 */
	public String getAttributes(){
		return SaladConstants.MODIFY_GRAVITY + "," + SaladConstants.GRAVITY_MAGNITUDE + "," + myMagnitude;
	}
	
	/**
	 * 
	 * @param GameObject object
	 */
	public void applyGravity(GameObject object){
		if (object == null) return;
		if(object.getName() == SaladConstants.SHOOT_NAME) return;
		if(object.ydir == -1){
			object.yspeed -= myMagnitude;
		}
		else if (object.ydir == 1){
			object.yspeed += myMagnitude;	
		}
	}

}
