package behaviors;

import java.util.List;

import engine.GameEngine;

public class WinByCollision extends Winnable{

	protected WinByCollision(GameEngine engine) {
		super(engine);
	}
    
	/**
	 * 
	 */
	@Override
	public boolean checkGoal(List<Object> params) {
		// TODO Auto-generated method stub
		return false;
	}

}
