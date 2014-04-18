package behaviors;

import objects.GameObject;

public class CanNotJump extends Jumpable {
	
	public CanNotJump(GameObject o){
		super(o);
	}
	
	public void jump(double magnitude){
		// do nothing
	}
}
