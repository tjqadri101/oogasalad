package behaviors;

import java.util.List;

import engine.GameEngine;

public abstract class Triggerable {
        
        protected GameEngine myEngine;
        
        protected Triggerable(GameEngine engine){
                myEngine = engine;
        }
        
        public abstract boolean checkTrigger (List<Object> params);

}
