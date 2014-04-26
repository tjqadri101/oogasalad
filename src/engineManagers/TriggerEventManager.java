package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import objects.Subject;
import engine.GameEngine;
import saladConstants.SaladConstants;

/**
 * TriggerManager keeps track of all the triggers and their corresponding events
 * @Author: Steve (Siyang) Wang
 */
public class TriggerEventManager implements Observer{
    //Re-think: maybe better relocate the checkTrigger and doEvent main body to here, 
    //instead of puttig into the object

    protected Map<Integer, List<Object>> myTriggerMap;
    protected Map<Integer, List<Object>> myEventMap;
    protected List<Object> mySubjectMap;
    protected GameEngine myEngine;
//    private TriggerEventManager _instance = null;
    
    public TriggerEventManager(GameEngine engine){
            myEngine = engine;
            myTriggerMap = new HashMap<Integer, List<Object>>();
            myEventMap = new HashMap<Integer, List<Object>>();
    }
    
    /**
     * Object, Event
     */
    public void setEventToObject(int ID, Object ... args){
            //need to implement the change on resourcebundle
            myEventMap.put(ID, );
    }
    
    /**
     * Object, Trigger
     */
    public void setTriggerToObject(int ID, Object ... args){
        //need to implement the change on resourcebundle
        myTriggerMap.put(ID, );
    }
    
    public void checkTrigger(){
        for (Object obj: myTriggerMap.keySet()){
            if(obj.checkTrigger(myEngine)){
                obj.doEvent(myEngine);
            }
        }
    }
    
    public List<String> getAttributes(){
            List<String> answer = new ArrayList<String>();
            for(Object state: myTriggerMap.keySet()){
                    answer.add(SaladConstants.MODIFY_TIMERMANAGER + "," + state + "," + myTriggerMap.get(state));
            }
            return answer;
    }
    
    public Object getEvent(Object obj){
            return myTriggerMap.get(obj);   
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Called initially to store subject as a watching subject
     */
    @Override
    public void setSubject (Object sub) {
        mySubjectMap.add(sub);
    }
	
}
