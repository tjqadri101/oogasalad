package engineManagers;

import java.util.HashMap;
import java.util.Map;

public class InputManager {
	
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

}
