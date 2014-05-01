package behaviors;

import java.util.List;

import engine.GameEngine;
/**
 * @param int time limit
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class TriggerByTime extends Triggerable{

	public TriggerByTime(GameEngine engine) {
		super(engine);
	}
    
	/**
	 * @param inttime limit
	 */
	@Override
	public boolean checkTrigger(List<Object> params) {
		int timeLimit = (Integer) params.get(0);
		if(myEngine.getSaladTimer() == timeLimit) return true;
		return false;
	}
}
