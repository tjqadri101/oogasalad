package behaviors;

import objects.GameObject;

public class CanNotRemove extends Removable{

	public CanNotRemove(GameObject o) {
		super(o);
	}

	@Override
	public void remove() {
		// do nothing
	}
}
