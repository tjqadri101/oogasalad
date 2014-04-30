package behaviors;

import engine.GameEngine;

public class KeyLevelDone extends Keyable{

	public KeyLevelDone(GameEngine engine) {
		super(engine);
	}

	@Override
	public void checkKey() {
		myEngine.levelDone();
	}

}
