package behaviors;

import java.util.List;

import engine.GameEngine;

public abstract class Winnable {
	
	protected GameEngine myEngine;
	
	protected Winnable(GameEngine engine){
		myEngine = engine;
	}
	
	public abstract boolean checkGoal(List<Object> params);

}
