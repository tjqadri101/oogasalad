package behaviors;

import java.util.List;

import engine.GameEngine;
/**
 * Switch to next Scene Event
 * @author Main Justin (Zihao) Zhang
 *
 */
public class EventSwitchScene extends Eventable {

	public EventSwitchScene(GameEngine engine) {
		super(engine);
	}

	/**
	 * @param int next scene ID
	 * @param double xpos
	 * @param double ypos
	 */
	@Override
	public void doEvent(List<Object> params) {
		int sceneID = (Integer) params.get(0);
		double xpos = (Double) params.get(1);
		double ypos = (Double) params.get(2);
		myEngine.switchScene(sceneID, xpos, ypos);
	}

}
