package engineManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saladConstants.SaladConstants;
import util.AttributeMaker;
/**
 * @Author: Justin (Zihao) Zhang
 * Only intended for non-player keys
 */
public class InputManager {
	
	public static final List<String> CHEAT_KEY_EVENTS = Arrays.asList(new String[]{
		"EnemyShower", "SceneDone", "BloodFull", "LifeIncrease", "GameOver"});
	
	/**
	 * Maps keys (i.e. 'G') to the name of the method in GameEngine
	 */
	protected Map<Integer, String> myKeyMap;
	
	public InputManager(){
		myKeyMap = new HashMap<Integer, String>();
	}
	
	public void setKey(int key, String methodName){
		myKeyMap.put(key, methodName);
	}
	
	public Map<Integer, String> getKeyMap(){
		return myKeyMap;
	}
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		for(int key: myKeyMap.keySet()){
			answer.add(SaladConstants.MODIFY_INPUTMANAGER + SaladConstants.SEPARATOR
					+ SaladConstants.SET_KEY + SaladConstants.SEPARATOR
					+ String.valueOf(key) + SaladConstants.SEPARATOR + myKeyMap.get(key));
		}
		return answer;
	}
}
