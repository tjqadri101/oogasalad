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

    protected Map<Object, List<?>> myTriggerMap;
    protected Map<Object, List<?>> myEventMap;
    protected List<?> mySubjectMap;
    protected GameEngine myEngine;
//    private TriggerEventManager _instance = null;
    
    public TriggerEventManager(GameEngine engine){
            myEngine = engine;
            myTriggerMap = new HashMap<Object, List<?>>();
            myEventMap = new HashMap<Object, List<?>>();
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
    
    public void checkTrigger(){
        for (int i = 0; i<mySubjectMap.size(); i++){
            if(mySubjectMap.get(i)).checkTrigger(myEngine)){
                mySubjectMap.get(i).doEvent(myEngine);
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
    
//Not implemented. Not sure if we use 100% observer pattern    
    /**implemented observer pattern
     * Called to see if any update is available on Observerside
     */
    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }
    
    /**implemented observer pattern
     * Called initially to store subject as a watching subject
     */
    @Override
    public void setSubject (Object sub) {
        mySubjectMap.add(sub);
    }
	
}
