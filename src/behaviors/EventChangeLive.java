package behaviors;

import java.util.List;

import engine.GameEngine;
/**
 * Change the live of a player
 * @author Main Justin (Zihao) Zhang
 *
 */
public class EventChangeLive extends Eventable{

	public EventChangeLive(GameEngine engine) {
		super(engine);
	}

	/**
	 * @param int playerID
	 * @param int change of blood
	 */
	@Override
	public void doEvent(List<Object> params) {
		int playerID = (Integer) params.get(0);
		int changeLive = (Integer) params.get(1);
		myEngine.getGame().getLiveManager().changeLive(playerID, changeLive);
	}

}
