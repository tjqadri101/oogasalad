package behaviors;

import java.util.List;

import engine.GameEngine;

public class WinByTileCollision extends Winnable{
	
	public WinByTileCollision(GameEngine engine){
		super(engine);
	}

	@Override
	public boolean checkGoal(List<Object> params) {
		return false;
	}

}
