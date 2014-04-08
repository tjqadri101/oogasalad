package behaviors;

import objects.GameObject;

public abstract class Jumpable {
	protected GameObject myObject; 
	
	public Jumpable(GameObject o){
		myObject = o;
	}
	
	public abstract void jump(double forceMagnitude);
}
