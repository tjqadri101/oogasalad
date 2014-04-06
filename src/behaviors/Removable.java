package behaviors;

import objects.GameObject;

public abstract class Removable {
	protected GameObject myObject; 
	
	public Removable(GameObject o){
		myObject = o;
	}
	
	public abstract void remove();
}
