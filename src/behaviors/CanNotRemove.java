package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * No parameters needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class CanNotRemove extends Removable{

	public CanNotRemove(GameObject o) {
		super(o);
	}

	@Override
	public void die(List<Object> params) {
		// do nothing
	}
}
