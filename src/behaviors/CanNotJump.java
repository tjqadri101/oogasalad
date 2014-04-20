package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * No parameters needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class CanNotJump extends Jumpable {
	
	public CanNotJump(GameObject o){
		super(o);
	}
	
	@Override
	public void jump(List<Object> params){
		// do nothing
	}
}
