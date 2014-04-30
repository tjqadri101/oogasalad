package behaviors;
import java.util.List;

import engine.GameEngine;
/**
 * The Game is lost
 * @author Main Justin (Zihao) Zhang
 *
 */
public class EventLoseGame extends Eventable{

	protected EventLoseGame(GameEngine engine) {
		super(engine);
	}

	@Override
	public void doEvent(List<Object> params) {
		myEngine.gameOver();
	}

}
