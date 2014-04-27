package behaviors;

import java.util.List;

import engine.GameEngine;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class Triggerable {
	
	protected GameEngine myEngine;
	
	protected Triggerable(GameEngine engine){
		myEngine = engine;
	}
	
	public abstract boolean checkTrigger(List<Object> params);

}
