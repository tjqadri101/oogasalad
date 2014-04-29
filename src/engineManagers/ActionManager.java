package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import objects.GameObject;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manager the actions for a Game Object
 * For every new action X added to a Game Object, a setXBehavior, an X() and a makeXAttribute methods 
 * are needed to add in this class
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class ActionManager {

	protected ResourceBundle myBehaviors;
	protected ResourceBundle myBehaviorMethods;
	protected List<String> myAttributes;
	protected GameObject myObject;
	protected List<String> myActions;
	protected Map<String, List<Object>> myActionMap;
	
	public ActionManager(GameObject object){
		myObject = object;
		myBehaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
				+ SaladConstants.OBJECT_BEHAVIOR);
		myBehaviorMethods = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
				+ SaladConstants.BEHAVIOR_METHOD);
		myAttributes = new ArrayList<String>();
		myActions = new ArrayList<String>();
		myActionMap = new HashMap<String, List<Object>>();
	}
	
	/**
	 * Set the action behavior
	 * @param s
	 * @param args
	 */
	public void setBehavior(String s, Object ... args){
		myActions.add(s);
		myActionMap.put(s, SaladUtil.convertArgsToObjectList(args));
	}
	
	/**
	 * Perform an action
	 * @param type
	 */
	public void doAction(String type){
		for(String action: myActions){
			String current = myBehaviorMethods.getString(action);
			if(current.equals(type)){
				SaladUtil.behaviorReflection(myBehaviors, action,
						myActionMap.get(action), type, myObject);
			}
		}
	}
	
	/**
	 * Called by Actor to rebounce if regular move behavior
	 */
	public void bounce(){
		for(String action: myActions){
			String current = myBehaviorMethods.getString(action);
			if(current.equals(SaladConstants.MOVE) && action == SaladConstants.REGULAR_MOVE){
				double xspeed = (Double) myActionMap.get(action).get(0);
				double yspeed = (Double) myActionMap.get(action).get(1);
				List<Object> newParams = new ArrayList<Object>();
				newParams.add(-1*xspeed);
				newParams.add(-1*yspeed);
				myActionMap.put(action, newParams);
				return;
			}
		}
		if (myObject.xspeed != 0 || myObject.yspeed != 0){
			myObject.xspeed *= -1;
			myObject.yspeed *= -1;
		}
	}
	
	public List<String> getAttributes(){
		for(String action: myActions){
			myAttributes.add(AttributeMaker.addAttribute(myObject.modificationString(),
					SaladConstants.ID, myObject.getID(), action, true,
					myActionMap.get(action)));	
		}
		return myAttributes;
	}
	
}
