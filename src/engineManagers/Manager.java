package engineManagers;

import java.util.HashMap;
import java.util.Map;

import objects.GameObject;
import saladConstants.SaladConstants;
import util.SaladUtil;

public abstract class Manager { 
	
	protected int myValue;
	protected int myInitValue;
	protected Map<String, Integer> myMap;
	
	protected Manager(int startValue){
		myInitValue = startValue;
		restore();
		myMap = new HashMap<String, Integer>();
	}
	
	public void setInitValue(int startValue){
		myInitValue = startValue;
	}
	
	public void restore(){
		myValue = myInitValue;
	}
	
	public int getCurrentValue(){
		return myValue;
	}
	
	public void setValue(int value, Object ... args){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, args);
		myMap.put(condition, value);
	}
	
	public void update(String info, GameObject victim, GameObject hitter){
		String condition = info + SaladConstants.SEPARATOR + victim.colid + 
				SaladConstants.SEPARATOR + hitter.colid;
		if(myMap.get(condition) == null) return;
		myValue += myMap.get(condition);
		System.out.println("ScoreManager current score: " + myValue);
	}
	
	public void update(String oldLevelOrSceneID, String newLevelOrSceneID){
		String condition = oldLevelOrSceneID + SaladConstants.SEPARATOR + newLevelOrSceneID;
		if(myMap.get(condition) == null) return;
		myValue += myMap.get(condition);
	}
	
	public void update(String condition){
		if(myMap.containsKey(condition)){
			myValue += myMap.get(condition);
		}
	}
	
	public abstract String getAttributes();

}
