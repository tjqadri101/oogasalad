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
    
    public void initTEM (GameEngine engine) {
        myEngine = engine;
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
//        System.out.println("TEM: checkCollisionTrigger: called here");
        for (Map.Entry<Integer, List<Object>> entry: myTriggerMap.entrySet()){
            List<Object> collisionPara = entry.getValue();
            try{
                if (collisionPara.size()==3){
                    if (compareParameters(collisionPara,collisionBehavior,victim,obj)){
//                        System.out.println("checkCollisionBehavior called: triggerEventManager");
                        performEvent(myEngine,entry.getKey());
                    }
                } 
            }catch (IndexOutOfBoundsException e){
                // it should not reach there
            }
        }
    }

    /** 
     * Called by engine in the doFrame
     */
    public void checkTrigger () {
    	
        for (Entry<Integer, List<Object>> entry : myTriggerMap.entrySet()) {
//            System.out.println("checkTrigger in TEM called: ");
            int etPairID = entry.getKey();
            List<Object> triggerList = entry.getValue();
//            System.out.println("checkTrigger: " + triggerList);
            if(triggerList.size()!=0){
                String triggerBehavior = triggerList.get(0).toString();
                triggerList = triggerList.subList(1, triggerList.size());
                if (triggerBehavior == null)
                    break;
                ResourceBundle behaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
                                   + SaladConstants.OBJECT_BEHAVIOR);
                Object answer = SaladUtil.behaviorReflection(behaviors, triggerBehavior,
                                                             triggerList, CHECK_TRIGGER, myEngine);
//                System.out.println("checkTrigger: the answer is " + answer);
                if ((boolean) answer)
                    performEvent(myEngine, etPairID);
//                    answer = false;
            }
        }
    }

    public void performEvent (GameEngine myEngine, int etPairID) {
//        System.out.println("performEvent is called in Manager");
        List<Object> rawPara = myEventMap.get(etPairID);
        int size = rawPara.size();
//        eventParameter.remove(0);
        List<Object> eventParameter = rawPara.subList(1, size);
//        System.out.println("doEvent: the eventParameter is " + eventParameter);
        String eventBehavior = (String) rawPara.get(0);
//        System.out.println("doEvent: eventBehavior is " + eventBehavior);
//        System.out.println("performEvent is called here ");
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
//        System.out.println("setEventOrTriggerBehavior: behaviroParar: " + behaviorParameters);
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
    
    public Map<Integer, List<Object>> getTriggerMap(){
        return myTriggerMap;
    }
    
    public Map<Integer, List<Object>> getEventMap(){
        return myEventMap;
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
