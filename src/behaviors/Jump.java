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
		double magnitude = (Double) params.get(0);
		int times = (Integer) params.get(1);
		if(times == 0)
			if(myObject.getIsInAir()) return;
		else
			if(myObject.getJumpTimes() > times) return;
		myObject.ydir = 1;
		myObject.yspeed -= magnitude;
	}
}
