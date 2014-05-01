package behaviors;

import java.util.List;

import engine.GameEngine;

public class KeyLevelDone extends Keyable{

	public KeyLevelDone(GameEngine engine) {
		super(engine);
	}

	@Override
	public void checkKey(List<Object> params) {
		System.out.println("EventLevelDone");
		myEngine.levelDone();
	}

}
