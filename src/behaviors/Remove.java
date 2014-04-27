package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * No parameters needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class Remove extends Removable{

	public Remove(GameObject o) {
		super(o);
	}

	@Override
	public void remove(List<Object> params) {
		//myObject.getTriggerEventManager().update(SaladConstants.COLLISION, myObject, hitter);
		myObject.remove();
	}
}
