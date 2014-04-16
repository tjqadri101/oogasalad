package behaviors;

import objects.GameObject;

public class Jump extends Jumpable{
	protected GameObject myObject; 
	
	public Jump(GameObject o){
		super(o);
	}
	
	public void jump(double magnitude){
//		myObject.setForce(0, forceMagnitude); //need to check the sign
	}
}
