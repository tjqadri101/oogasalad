package engineManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.GameEngine;
import saladConstants.SaladConstants;
import util.AttributeMaker;
/**
 * @author Main Justin (Zihao) Zhang
 * Only intended for non-player keys
 */
public class InputManager {
	
	public static final List<String> CHEAT_KEY_EVENTS = Arrays.asList(new String[]{
		"EnemyShower", "SceneDone", "BloodFull", "LifeIncrease", "GameOver"});
	protected GameEngine myEngine;
	
	/**
	 * Maps keys (i.e. 'G') to the name of the method in GameEngine
	 */
	protected Map<Integer, String> myKeyMap;
	
	public InputManager(){
		myKeyMap = new HashMap<Integer, String>();
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
		for(int key: myKeyMap.keySet()){
			if(myEngine.getKey(key)){
				
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
	
	//may be deleted
	public Map<Integer, String> getKeyMap(){
		return myKeyMap;
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
