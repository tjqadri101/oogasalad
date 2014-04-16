package behaviors;

import objects.GameObject;

public class NonJumpable {
	protected GameObject myObject; 
	
	public NonJumpable(GameObject o){
		myObject = o;
	}
	
	public void jump(double forceMagnitude){
//		myObject.setForce(0, 0); 
	}
}
