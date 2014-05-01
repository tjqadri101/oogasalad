package behaviors;

import java.util.List;

import engine.GameEngine;
/**
 * Add or subtract some blood from the player(s)
 * @author Main Justin (Zihao) Zhang
 *
 */
public class EventChangeBlood extends Eventable{

	public EventChangeBlood(GameEngine engine) {
		super(engine);
	}

	/**
	 * @param int playerID
	 * @param int change of blood
	 */
	@Override
	public void doEvent(List<Object> params) {
		int playerID = (Integer) params.get(0);
		int changeBlood = (Integer) params.get(1);
		myEngine.getGame().getPlayer(playerID).changeBlood(changeBlood);
	}
	
	

}
