package behaviors;

import java.util.List;

import engine.GameEngine;

public class WinByTime extends Winnable{

	public WinByTime(GameEngine engine) {
		super(engine);
	}
    
	/**
	 * @param time limit
	 */
	@Override
	public boolean checkGoal(List<Object> params) {
		int timeLimit = (Integer) params.get(0);
		if(myEngine.timer >= timeLimit) return true;
		return false;
	}

}
