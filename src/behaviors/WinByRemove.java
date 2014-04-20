package behaviors;

import java.util.List;
import jgame.JGObject;
import objects.NonPlayer;
import objects.Player;

import engine.GameEngine;

public class WinByRemove extends Winnable{

	public WinByRemove(GameEngine engine) {
		super(engine);
	}
	
	    
        /**
         * @param target, player
         */
        @Override
        public boolean checkGoal(List<Object> params) {
//              System.out.println("checkGoal called " + myEngine.timer + " " + timeLimit);
                NonPlayer target = (NonPlayer) params.get(0);
                return !target.isAlive();
        }

}
