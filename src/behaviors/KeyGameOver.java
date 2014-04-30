package behaviors;

import java.util.List;

import engine.GameEngine;

public class KeyGameOver extends Keyable{

	public KeyGameOver(GameEngine engine) {
		super(engine);
	}

	@Override
	public void checkKey(List<Object> params) {
		myEngine.gameOver();
	}

}
