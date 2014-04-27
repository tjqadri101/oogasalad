package behaviors;

import java.util.List;
import engine.GameEngine;
/**
* @author Main Steve (Siyang) Wang
*/
public class TriggerByTime extends Triggerable{

        public TriggerByTime(GameEngine engine) {
                super(engine);
        }
    
        /**
         * @param Time limit
         */
        @Override
        public boolean checkTrigger(List<Object> params) {
                int timeLimit = (Integer) params.get(0);
//      System.out.println("Trigger Event called " + myEngine.timer + " " + timeLimit);
                if(myEngine.timer >= timeLimit) return true;
                return false;
        }

}