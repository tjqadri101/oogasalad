package objects;

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
		object.yspeed += myMagnitude;
	}

}
