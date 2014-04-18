package behaviors;

import objects.GameObject;

public class CanNotJump extends Jumpable {
	
	public CanNotJump(GameObject o){
		super(o);
	}
	
	@Override
	public void jump(Object ... args){
		// do nothing
	}
}
