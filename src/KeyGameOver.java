import engine.GameEngine;
import behaviors.Keyable;


public class KeyGameOver extends Keyable {

	public KeyGameOver(GameEngine engine) {
		super(engine);
	}

	@Override
	public void checkKey() {
		myEngine.gameOver();
	}

}
