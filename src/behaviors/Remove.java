package behaviors;

import java.util.List;

import objects.GameObject;

public class Remove extends Removable{

	public Remove(GameObject o) {
		super(o);
	}

	@Override
	public void remove(List<Object> params) {
		myObject.loseLife();
		if(myObject.getLives() <= 0){
			myObject.remove();	
		}
	}
}
