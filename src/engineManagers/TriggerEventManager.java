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
import engineManagers.*;

/**
 * TriggerManager keeps track of all the triggers and their corresponding events
 * @Author: Steve (Siyang) Wang
 */
public class TriggerEventManager implements Observer{

    protected Map<Integer, List<Object>> myTriggerMap;
    protected Map<Integer, List<Object>> myEventMap;
    protected Game myGame;
    protected List<Class> mySubjectList;
    protected GameEngine myEngine;
    protected Integer myCurrentLevel;
    protected Integer myCurrentScene;

    private TriggerEventManager(){
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

    
    // to work on later when setting the winning behavior
    public void setTriggerBehavior(String type, Object ... args){
        myWinBehavior = type;
        myWinParameters = new ArrayList<Object>();
        for(int i = 0; i < args.length; i ++){
            myWinParameters.add(args[i]);
        }
    }
    
    public void setEventBehavior(String type, Object ... args){
        myWinBehavior = type;
        myWinParameters = new ArrayList<Object>();
        for(int i = 0; i < args.length; i ++){
            myWinParameters.add(args[i]);
        }
    }

    public List<String> getAttributes(){
        List<String> answer = new ArrayList<String>();
        for(Object state: myTriggerMap.keySet()){
            answer.add(SaladConstants.MODIFY_TIMERMANAGER + "," + state + "," + myTriggerMap.get(state));
        }
        return answer;
    }

    /**
     * Object, Event
     */
    public void setEventToObject(int ID, Object ... args){
        //need to implement the change on resourcebundle
        Object obj = myEngine.getCurrent
                myEventMap.put(ID, );
    }

    /**
     * Object, Trigger
     */
    public void setTriggerToObject(int ID, Object ... args){
        //need to implement the change on resourcebundle
        myTriggerMap.put(ID, );
    }


    //Not implemented. Not sure if we use 100% observer pattern    
    /**implemented observer pattern
     * Called from the subject to show information 
     */
    @Override
    public void update () {

    }
    /**implemented observer pattern
     * Called initially to store subject as a watching subject
     */
    @Override
    @SuppressWarnings("rawtypes")
    public void setSubject (Class sub) {
        mySubjectList.add(sub);
    }

    public Object getEvent(Object obj){
        return myTriggerMap.get(obj);   
    }

}
