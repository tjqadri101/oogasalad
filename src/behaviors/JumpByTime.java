package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
/**
 * @param double magnitude
 * @param int jump times allowed while in the air
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class JumpByTime extends Jumpable{
	
	public JumpByTime(GameObject o){
		super(o);
	}
	
	/**
	 * 
	 * @param double magnitude
	 * @param int jump times allowed while in the air
	 * @param int time latency; the larger the slower
	 */
	@Override
	public void jump(List<Object> params){
		GameEngine engine = (GameEngine) myObject.eng;
		double magnitude = (Double) params.get(0);
		int times = (Integer) params.get(1);
		int latency = (Integer) params.get(2);
		
		if(myObject.getJumpTimes() > times){return;}
		if((engine.getSaladTimer() + latency) % (latency) == 0){
			myObject.ydir = 1;
			myObject.yspeed -= magnitude;	
		}
	}
}
