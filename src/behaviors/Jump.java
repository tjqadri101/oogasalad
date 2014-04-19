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
		double magnitude = (Double) params.get(0);
		myObject.yspeed -= magnitude;
	}
}
