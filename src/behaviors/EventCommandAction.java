package behaviors;

import java.util.List;
import reflection.Reflection;
import engine.GameEngine;

public class EventCommandAction extends Eventable {
        private GameEngine myEngine;

    protected EventCommandAction (GameEngine engine) {
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
        Reflection.callMethod(obj, behavior, args);
    }

}
