package behaviors;

import java.util.List;

import objects.GameObject;

public class CanNotRemove extends Removable{

	public CanNotRemove(GameObject o) {
		super(o);
	}

	@Override
	public void remove(List<Object> params) {
		// do nothing
	}
}
