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
import util.SaladUtil;

/**
 * TriggerManager keeps track of all the triggers and their corresponding events
 * @Author: Steve (Siyang) Wang
 */
public class TriggerEventManager {

    protected Map<Integer, List<Object>> myTriggerMap;
    protected Map<Integer, List<Object>> myEventMap;
    protected Game myGame;
    protected List<Class> mySubjectList;
    protected GameEngine myEngine;
    protected Integer myCurrentLevel;
    protected Integer myCurrentScene;

    public TriggerEventManager(){
        myGame = myEngine.getGame();
        myTriggerMap = new HashMap<Integer, List<Object>>();
        myEventMap = new HashMap<Integer, List<Object>>();
        mySubjectList = new ArrayList<>(); 
    }

    public void checkTrigger(GameEngine myEngine){
        for (Entry<Integer, List<Object>> entry: myTriggerMap.entrySet()){
            int etPairID = entry.getKey();
            List<Object> triggerList = entry.getValue(); 
            String triggerBehavior = (String) triggerList.remove(0);
            if (triggerBehavior == null)
                break;
            //            List<Object> triggerParameters = myGame.getLevel(myCurrentLevelID)
            //                    .getWinParameters();
            ResourceBundle behaviors = ResourceBundle
                    .getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
                               + SaladConstants.OBJECT_BEHAVIOR);
            Object answer = SaladUtil.behaviorReflection(behaviors, triggerBehavior,
                                                         triggerList, "checkTrigger", myEngine);
            if ((boolean) answer)
                doEvent(myEngine, etPairID);
        }
    }

    private void doEvent(GameEngine myEngine, int etPairID){
        List<Object> eventParameter = myEventMap.get(etPairID);
        String eventBehavior = (String) eventParameter.remove(0);
        ResourceBundle behaviors = ResourceBundle
                .getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
                           + SaladConstants.OBJECT_BEHAVIOR);
        SaladUtil.behaviorReflection(behaviors, eventBehavior, eventParameter, "doEvent", myEngine);
    }
    
    public void setTriggerBehavior(int etPairID, String triggerBehavior, Object ... args){
        List<Object> triggerParameters = new ArrayList<Object>();
        triggerParameters.add(triggerBehavior);
        for(int i = 0; i < args.length; i ++){
            triggerParameters.add(args[i]);
        }
        myTriggerMap.put(etPairID, triggerParameters);
    }
    
    public void setEventBehavior(int etPairID, String eventBehavior, Object ... args){
        List<Object> eventParameters = new ArrayList<Object>();
        eventParameters.add(eventBehavior);
        for(int i = 0; i < args.length; i ++){
            eventParameters.add(args[i]);
        }
        myEventMap.put(etPairID, eventParameters);
    }
    
    public void updateTEM(){
        
    }

    public List<String> getAttributes(){
        List<String> answer = new ArrayList<String>();
        for(Object state: myTriggerMap.keySet()){
            answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_TRIGGER_EVENT_MANAGER + "," + state + "," + myTriggerMap.get(state));
        }
        return answer;
    }

}
