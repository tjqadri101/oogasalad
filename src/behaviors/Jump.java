package behaviors;

import objects.GameObject;

public class Jump extends Jumpable{
	
	public Jump(GameObject o){
		super(o);
	}
	
	public void jump(double magnitude){
		if(!myObject.getIsAir()){
			myObject.yspeed -= magnitude;
			myObject.setIsAir(true);
		}
		if(myObject.getIsAir()) {
			myObject.yspeed+=0.1;
		}
	}
}
