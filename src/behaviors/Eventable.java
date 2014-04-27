package behaviors;

import java.util.List;

import engine.GameEngine;

public abstract class Eventable{
        
        protected GameEngine myEngine;
        
        protected Eventable(GameEngine engine){
                myEngine = engine;
        }
        
        public abstract void doEvent(List<Object> params);

}
