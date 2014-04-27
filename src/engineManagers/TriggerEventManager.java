package engineManagers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import objects.GameObject;
import objects.Subject;
import engine.GameEngine;
import saladConstants.SaladConstants;
import stage.Game;
import util.AttributeMaker;
import util.SaladUtil;


/**
 * TriggerManager keeps track of all the triggers and their corresponding events
 * 
 * @Author: Steve (Siyang) Wang
 */
public class TriggerEventManager {

    private static final String DO_EVENT = "doEvent";
    private static final String CHECK_TRIGGER = "checkTrigger";
    private static final String TRIGGER_INDICATOR = "Trigger";
    protected Map<Integer, List<Object>> myTriggerMap;
    protected Map<Integer, List<Object>> myEventMap;
    protected Game myGame;
    protected List<Class> mySubjectList;
    protected GameEngine myEngine;
    protected Integer myCurrentLevel;
    protected Integer myCurrentScene;
    protected List<String> myAttributes;

    public TriggerEventManager () {
        myGame = myEngine.getGame();
        myTriggerMap = new HashMap<Integer, List<Object>>();
        myEventMap = new HashMap<Integer, List<Object>>();
        mySubjectList = new ArrayList<>();
        myAttributes = new ArrayList<>();
    }

    public void checkTrigger (GameEngine myEngine) {
        for (Entry<Integer, List<Object>> entry : myTriggerMap.entrySet()) {
            int etPairID = entry.getKey();
            List<Object> triggerList = entry.getValue();
            String triggerBehavior = (String) triggerList.remove(0);
            if (triggerBehavior == null)
                break;
            ResourceBundle behaviors = ResourceBundle
                    .getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
                               + SaladConstants.OBJECT_BEHAVIOR);
            Object answer = SaladUtil.behaviorReflection(behaviors, triggerBehavior,
                                                         triggerList, CHECK_TRIGGER, myEngine);
            if ((boolean) answer)
                doEvent(myEngine, etPairID);
        }
    }

    private void doEvent (GameEngine myEngine, int etPairID) {
        List<Object> eventParameter = myEventMap.get(etPairID);
        String eventBehavior = (String) eventParameter.remove(0);
        ResourceBundle behaviors = ResourceBundle
                .getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
                           + SaladConstants.OBJECT_BEHAVIOR);
        SaladUtil.behaviorReflection(behaviors, eventBehavior, eventParameter, DO_EVENT, myEngine);
    }

    public void setEventOrTriggerBehavior (int etPairID, String behaviorName, Object ... args) {
        List<Object> behaviorParameters = new ArrayList<Object>();
        behaviorParameters.add(behaviorName);
        for (int i = 0; i < args.length; i++) {
            behaviorParameters.add(args[i]);
        }
        if (behaviorName.contains(TRIGGER_INDICATOR)) {
//            String attribute = AttributeMaker.addAttribute(SaladConstants.MODIFY_TRIGGER_EVENT_MANAGER,
//                                                SaladConstants.COLLISION_ID, args[1], args[2], true, args[2]);
            myTriggerMap.put(etPairID, behaviorParameters);
        }
        else {
            myEventMap.put(etPairID, behaviorParameters);
        }
    }

    public List<String> getAttributes(){
        return myAttributes;
    }
    
    // See if putting same methods together works
    /*
     * public void setEventBehavior(int etPairID, String eventBehavior, Object ... args){
     * List<Object> eventParameters = new ArrayList<Object>();
     * eventParameters.add(eventBehavior);
     * for(int i = 0; i < args.length; i ++){
     * eventParameters.add(args[i]);
     * }
     * myEventMap.put(etPairID, eventParameters);
     * }
     */

}
