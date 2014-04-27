package behaviors;

import java.util.List;

import engine.GameEngine;
/**
 * @param int time limit
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class TriggerEventByTime extends Triggerable{

	public TriggerEventByTime(GameEngine engine) {
		super(engine);
	}
    
	/**
	 * @param inttime limit
	 */
	@Override
	public boolean checkTrigger(List<Object> params) {
		int timeLimit = (Integer) params.get(0);
//    	System.out.println("checkGoal called " + myEngine.timer + " " + timeLimit);
		if(myEngine.timer >= timeLimit) return true;
		return false;
	}

}
