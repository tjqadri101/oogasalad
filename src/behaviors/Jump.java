package behaviors;

import objects.GameObject;

public class Jump extends Jumpable{
	protected GameObject myObject; 
	
	public Jump(GameObject o){
		super(o);
	}
	
	public void jump(double magnitude){
//		myObject.setForce(0, forceMagnitude); //need to check the sign
		if (myObject.eng.getKey('J')) {
			myObject.eng.clearKey('J');
			if(!myObject.in_the_air){
				myObject.yspeed -= 5;
				myObject.in_the_air = true;
			}
		}
		if(myObject.in_the_air){
			myObject.yspeed+=0.1;
		}
	}
}
