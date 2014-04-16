package behaviors;

import objects.GameObject;

public class Remove extends Removable{

	public Remove(GameObject o) {
		super(o);
	}

	@Override
	public void remove() {
		myObject.loseLife();
		if(myObject.getLives() <= 0){
			myObject.remove();	
		}
	}
}
