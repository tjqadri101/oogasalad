package objects;

import saladConstants.SaladConstants;

public class Gravity {
	
	protected double myMagnitude;
	
	public Gravity(){}
	
	public void setMagnitude(double magnitude){
		myMagnitude = magnitude;
	}
	
	public String getAttributes(){
		return null; // need revision
	}
	
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
