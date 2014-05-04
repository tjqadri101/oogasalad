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
 * This class is totally flexible in the sense that every newly added action will not result in any
 * code changes here
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
	 * @param behavior
	 * @param args
	 */
	public void setBehavior(String behavior, Object ... args){
		String type = myBehaviorMethods.getString(behavior);
		String delete = null;
		for(String action: myActions){
			if(myBehaviorMethods.getString(action).equals(type)) delete = action;
		}
		if(delete != null) myActions.remove(delete);
		myActions.add(behavior);
		myActionMap.put(behavior, SaladUtil.convertArgsToObjectList(args));
	}
	
	/**
	 * Perform an action for a certain behavior type (i.e. jump, move)
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
	 * Get a list of Strings that contains the attributes regarding all the actions for a Game Object
	 * @return
	 */
	public List<String> getAttributes(){
		for(String action: myActions){
			myAttributes.add(AttributeMaker.addAttribute(myObject.modificationString(),
					SaladConstants.ID, myObject.getID(), action, true,
					myActionMap.get(action)));	
		}
		return myAttributes;
	}
	
}
