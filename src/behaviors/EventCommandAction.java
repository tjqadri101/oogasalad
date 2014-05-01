package behaviors;

import java.util.List;
import reflection.Reflection;
import engine.GameEngine;
/**
 * 
 * @author Main Steve (Siyang) Wang
 *
 */
public class EventCommandAction extends Eventable {
        private GameEngine myEngine;

    public EventCommandAction (GameEngine engine) {
        super(engine);
        myEngine = engine;
    }

    /**
     * Called via reflection to invoke the doEvent method
     * @param ObjectID
     * @param behavior
     * @param 
     */
    @Override
    public void doEvent (List<Object> params) {
        int myCurrentLevelID = myEngine.getCurrentLevelID();
        int myCurrentSceneID = myEngine.getCurrentSceneID();
        int ObjectID = (int) params.get(0);
        String behavior  = (String) params.get(1);
        List<Object> trimmedParam = params.subList(2, params.size());
        Object[] args = trimmedParam.toArray(new Object[trimmedParam.size()]);
        Object obj = myEngine.getGame().getLevel(myCurrentLevelID).getScene(myCurrentSceneID);
        //Justin: you reflect on the wrong method. There is only one action method in GameObject
        //which is doAction. There is no shoot, jump, die, etc.. if you want to call shoot, you have
        //to call object.doAction("shoot"), which takes no parameters. If you are modifying a behavior of 
        //a GameObject, you should call setBehavior(behavior, Object ... args)
        //After that, you can call doAction("XXX") to perform that action
        Reflection.callMethod(obj, behavior, args);
    }

}
