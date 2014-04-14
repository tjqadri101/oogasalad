package behaviors;

import objects.GameObject;

public class Remove extends Removable{

	public Remove(GameObject o) {
		super(o);
	}

	@Override
	public void remove() {
//		myObject.destroy();
		myObject.remove();
	}
}
