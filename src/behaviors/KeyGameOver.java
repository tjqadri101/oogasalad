package behaviors;

import engine.GameEngine;

public class KeyGameOver extends Keyable{

	public KeyGameOver(GameEngine engine) {
		super(engine);
	}

	@Override
	public void checkKey() {
		myEngine.gameOver();
	}

}
