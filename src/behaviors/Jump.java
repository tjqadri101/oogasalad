package behaviors;

import objects.GameObject;

public class Jump extends Jumpable{
	
	public Jump(GameObject o){
		super(o);
	}
	
	public void jump(double magnitude){
			myObject.yspeed -= magnitude;
	}
}
