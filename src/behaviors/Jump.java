package behaviors;

import objects.GameObject;

public class Jump extends Jumpable{
	
	public Jump(GameObject o){
		super(o);
	}
	
	/**
	 * 
	 * @param magnitude
	 */
	@Override
	public void jump(Object ... args){
		double magnitude = (Double) args[0];
		myObject.yspeed -= magnitude;
	}
}
