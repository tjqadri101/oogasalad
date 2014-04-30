package behaviors;

import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
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
		
		if (myObject.getJumpTimes() >= times) return;
		myObject.setYHead(SaladConstants.POSITIVE_DIRECTION);
		myObject.ydir = SaladConstants.POSITIVE_DIRECTION;
		myObject.yspeed -= magnitude;
		if (!myObject.getInAir()) { myObject.incrementJumpTimes(1); }
//		if (myObject.getAirCounter() == 0 ) { myObject.incrementJumpTimes(1); }
		myObject.setInAir(true);
		myObject.updateImage(SaladConstants.UPDATE_JUMP);
	}
}
