package behaviors;

import java.util.List;

import objects.GameObject;

public class CanNotJump extends Jumpable {
	
	public CanNotJump(GameObject o){
		super(o);
	}
	
	@Override
	public void jump(List<Object> params){
		// do nothing
	}
}
