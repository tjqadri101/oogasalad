package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import engine.GameEngine;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manage all the non-player keys, including cheatKeys
 * This class uses composition over inheritance so that any new non-player keys do not need to add 
 * code here
 * @author Main Justin (Zihao) Zhang
 */
public class InputManager {
	
	protected ResourceBundle myKeyMethods;
	protected GameEngine myEngine;
	
	/**
	 * Maps keys (i.e. 'G') to the name of the method in GameEngine
	 */
	protected Map<Integer, String> myKeyMap;
	
	public InputManager(){
		myKeyMap = new HashMap<Integer, String>();
		myKeyMethods = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
				+ SaladConstants.OBJECT_BEHAVIOR);
	}
	
	/**
	 * Called by engine while the Game is loaded
	 * @param engine
	 */
	public void initCheckKey(GameEngine engine){
		myEngine = engine;
	}
	
	/**
	 * Called by engine in each doFrame to check keys
	 */
	public void checkKey(){
		if(myEngine == null) return;
		for(int key: myKeyMap.keySet()){
			if(myEngine.getKey(key)){
				List<Object> params = new ArrayList<Object>();
				SaladUtil.behaviorReflection(myKeyMethods, myKeyMap.get(key),
						params, SaladConstants.CHECK_KEY, myEngine);
				myEngine.clearKey(key);
			}
		}
	}
	
	/**
	 * Set a key to a method name
	 * @param key
	 * @param methodName
	 */
	public void setKey(int key, String methodName){
		myKeyMap.put(key, methodName);
	}
	
	/**
	 * Called to get the attribute of the input manager
	 * @return
	 */
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		for(int key: myKeyMap.keySet()){
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_INPUTMANAGER, 
					SaladConstants.SET_KEY, false, key, myKeyMap.get(key)));
		}
		return answer;
	}
}
