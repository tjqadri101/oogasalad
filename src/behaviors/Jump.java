package behaviors;

import objects.GameObject;

public class Jump {
	protected GameObject myObject; 
	
	public Jump(GameObject o){
		myObject = o;
	}
	
	public void jump(double forceMagnitude){
//		myObject.setForce(0, forceMagnitude); //need to check the sign
	}
}
