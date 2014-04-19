package behaviors;

import java.util.List;

import objects.GameObject;

public abstract class Jumpable {
	protected GameObject myObject; 
	
	public Jumpable(GameObject o){
		myObject = o;
	}
	
	public abstract void jump(List<Object> params);
}
