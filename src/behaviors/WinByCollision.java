package behaviors;

import java.util.List;
import jgame.JGObject;
import objects.NonPlayer;
import objects.Player;

import engine.GameEngine;

public class WinByCollision extends Winnable{

	protected WinByCollision(GameEngine engine) {
		super(engine);
	}
	
	    
        /**
         * @param target, player
         */
        @Override
        public boolean checkGoal(List<Object> params) {
//              System.out.println("checkGoal called " + myEngine.timer + " " + timeLimit);
                NonPlayer target = (NonPlayer) params.get(0);

/*can either check hit, or check the life value of the player. Latter is better, but how to 
decrement when the NonPlayer is hitted?*/
                        
                return !target.isAlive();
        }

}
