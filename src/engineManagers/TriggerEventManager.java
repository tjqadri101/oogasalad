package engineManagers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import objects.GameObject;
import objects.Subject;
import engine.GameEngine;
import saladConstants.SaladConstants;
import engineManagers.*;

/**
 * TriggerManager keeps track of all the triggers and their corresponding events
 * @Author: Steve (Siyang) Wang
 */
public class TriggerEventManager implements Observer{

    protected Map<Object, List<?>> myTriggerMap;
    protected Map<Object, List<?>> myEventMap;
    protected List<Class> mySubjectList;
    protected GameEngine myEngine;

    public TriggerEventManager(GameEngine engine){

        myEngine = engine;
        myTriggerMap = new HashMap<Object, List<?>>();
        myEventMap = new HashMap<Object, List<?>>();
        mySubjectList = new ArrayList<>();
    }

    //Not implemented. Not sure if we use 100% observer pattern    
    /**implemented observer pattern
     * Called from the subject to show information 
     */
    @Override
    public void update () {

    }

    public void checkTrigger(){
        for (int i = 0; i<mySubjectList.size(); i++){
            Object cast = mySubjectList.get(i).getClass();
            if ((cast) mySubjectList.get(i).checkTrigger(myEngine)){
                //use reflection to reflect on the particular event, doEvent
                mySubjectList.get(i).doEvent(myEngine);
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
