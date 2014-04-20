package behaviors;

import java.util.List;

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
	public void jump(List<Object> params){
//		if(myObject.getIsInAir() == true) return;
		double magnitude = (Double) params.get(0);
		myObject.ydir = 1;
		myObject.yspeed -= magnitude;
	}
}
