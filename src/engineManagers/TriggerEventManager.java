package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import objects.GameObject;
import objects.SideDetector;
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
public class TriggerEventManager extends StatisticsManager{

    private static final String DO_EVENT = "doEvent";
    private static final String CHECK_TRIGGER = "checkTrigger";
    private static final String TRIGGER_INDICATOR = "Trigger";
    protected Map<Integer, List<Object>> myTriggerMap;
    protected Map<Integer, List<Object>> myEventMap;
    protected Game myGame;
    protected GameEngine myEngine;
    protected Integer myCurrentLevel;
    protected Integer myCurrentScene;
    protected List<String> myAttributes;

    public TriggerEventManager () {
        myTriggerMap = new HashMap<Integer, List<Object>>();
        myEventMap = new HashMap<Integer, List<Object>>();
        myAttributes = new ArrayList<>();
    }

    /**
     * Called by Collision when the trigger is collision
     * @param info: Collision Behavior
     */
    @Override
    public void update (String info, GameObject victim, GameObject hitter){
        //        List<Object> actual = new ArrayList();
        GameObject hitterObj= checkIfIsSideDetector(hitter);
        GameObject victimObj= checkIfIsSideDetector(victim);
        checkCollisionTrigger(info, hitterObj, victimObj);
    }
    
    /**
     * Called by TileCollision when the trigger is TileCollision
     */
    @Override
    public void update (String info, GameObject victim, int tilecid){
        GameObject victimObj= checkIfIsSideDetector(victim);
        checkCollisionTrigger(info, victimObj, tilecid);
    }

    /**
     * @param collisionBehavior
     * @param hitterObj
     * @param victimObj
     */
    private void checkCollisionTrigger (String collisionBehavior,
                                        GameObject victim,
                                        Object obj) {
        for (Map.Entry<Integer, List<Object>> entry: myTriggerMap.entrySet()){
            List<Object> collisionPara = entry.getValue();
            try{
                if (collisionPara.size()==3){
                    if (compareParameters(collisionPara,collisionBehavior,victim,obj)){
                        doEvent(myEngine,entry.getKey());
                    }
                } 
            }catch (IndexOutOfBoundsException e){
                System.out.println("Caught IOException: " + e.getMessage());
            }
        }
    }

    /** 
     * Called by engine in the doFrame
     */
    public void checkTrigger (GameEngine engine) {
        myEngine = engine; 

        for (Entry<Integer, List<Object>> entry : myTriggerMap.entrySet()) {
            System.out.println("TriggerEventManager checkTrigger called: ");
            int etPairID = entry.getKey();
            List<Object> triggerList = entry.getValue();
            System.out.println(triggerList);
            if(triggerList.size()!=0){
                String triggerBehavior = triggerList.get(0).toString();
                triggerList = triggerList.subList(1, triggerList.size());
//                triggerList.remove(0);// this place causes error
                if (triggerBehavior == null)
                    break;
                System.out.println("TEM: behavior " + triggerBehavior);
                ResourceBundle behaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
                                   + SaladConstants.OBJECT_BEHAVIOR);
                Object answer = SaladUtil.behaviorReflection(behaviors, triggerBehavior,
                                                             triggerList, CHECK_TRIGGER, myEngine);
                // Nullpointer exception here... Consider going through salad reflection
                if ((boolean) answer)
                    doEvent(myEngine, etPairID);
            }
        }

    }

    private void doEvent (GameEngine myEngine, int etPairID) {
        List<Object> eventParameter = myEventMap.get(etPairID);
        System.out.println("doEvent: the eventParameter is " + eventParameter);
        String eventBehavior = (String) eventParameter.get(0);
        System.out.println("doEvent: eventBehavior is " + eventBehavior);
        ResourceBundle behaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
                           + SaladConstants.OBJECT_BEHAVIOR);
        SaladUtil.behaviorReflection(behaviors, eventBehavior, eventParameter, DO_EVENT, myEngine);
    }

    /** 
     * Called to test if sideDetector
     */
    protected GameObject checkIfIsSideDetector(GameObject object){
        if (object instanceof SideDetector){
            SideDetector detector = (SideDetector) object;
            return detector.getParent();
        }
        return object;
    }

    /** 
     * Called from main loop to compare parameters
     */
    private boolean compareParameters (List<Object> collisionPara,
                                       String collisionBehavior,
                                       GameObject myObject,
                                       Object hitter) {
        if(     (collisionPara.get(0).equals(collisionBehavior))&&
                collisionPara.get(1).equals(myObject)&&
                collisionPara.get(2).equals(hitter)){
            return true;
        }
        return false;
    }

    public void setEventOrTriggerBehavior (int etPairID, String behaviorName, Object ... args) {
        List<Object> behaviorParameters = new ArrayList<Object>();
        behaviorParameters.add(behaviorName);
        for (int i = 0; i < args.length; i++) {
            behaviorParameters.add(args[i]);
        }
        System.out.println("SetEventOrTriggerB: behaviroParar: " + behaviorParameters);
        if (behaviorName.contains(TRIGGER_INDICATOR)) {
            myTriggerMap.put(etPairID, behaviorParameters);
        }
        else {
            myEventMap.put(etPairID, behaviorParameters);
        }
        String attribute = AttributeMaker.addAttribute(SaladConstants.MODIFY_TRIGGER_EVENT_MANAGER,
                                                       SaladConstants.COLLISION_ID, etPairID, behaviorName, true, args);
        myAttributes.add(attribute);
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
