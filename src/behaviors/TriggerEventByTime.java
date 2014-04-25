package behaviors;

import java.util.List;

import engine.GameEngine;

public class TriggerEventByTime extends Winnable{

        public TriggerEventByTime(GameEngine engine) {
                super(engine);
        }
    
        /**
         * @param Time limit
         */
        @Override
        public boolean checkGoal(List<Object> params) {
                int timeLimit = (Integer) params.get(0);
//      System.out.println("Trigger Event called " + myEngine.timer + " " + timeLimit);
                if(myEngine.timer >= timeLimit) return true;
                return false;
        }

}