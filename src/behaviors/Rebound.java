package behaviors;

import java.util.List;

import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
/**
 * Only the hitter rebounces while hitting the victim
 * The hitter cannot be a player
 * @author Main Justin (Zihao) Zhang
 */
public class Rebound extends Collision{

	public Rebound(GameObject o) {
		super(o);
	}

	@Override
	public void collide(List<Object> objects) {
		GameObject hitter = (GameObject) objects.get(0);
		updateManagers(hitter);
		if(hitter instanceof NonPlayer){
			hitter.setBehavior(SaladConstants.REGULAR_MOVE, -1*hitter.xspeed, -1*hitter.yspeed);
		}
		else{ myObject.stop(); }
	}
}
