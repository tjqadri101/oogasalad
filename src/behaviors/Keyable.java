package behaviors;


import java.util.List;

import engine.GameEngine;

public abstract class Keyable {
	
	protected GameEngine myEngine;
	
	protected Keyable (GameEngine engine){
		myEngine = engine;
	}
	
	public abstract void checkKey(List<Object> params);

}
