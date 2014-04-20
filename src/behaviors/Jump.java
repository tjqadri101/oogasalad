package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * @param double magnitude
 * @param int jump times allowed while in the air
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class Jump extends Jumpable{
	
	public Jump(GameObject o){
		super(o);
	}
	
	/**
	 * 
	 * @param double magnitude
	 * @param int jump times allowed while in the air
	 */
	@Override
	public void jump(List<Object> params){
//		if(myObject.getIsInAir() == true) return;
		double magnitude = (Double) params.get(0);
		int times = (Integer) params.get(1);
		myObject.ydir = 1;
		myObject.yspeed -= magnitude;
	}
}
