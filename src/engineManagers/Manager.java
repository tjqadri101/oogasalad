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
	
	/**
	 * Set the initial Value
	 * @param startValue
	 */
	public void setInitValue(int startValue){
		myInitValue = startValue;
	}
	
	/**
	 * Restore to the initial value
	 */
	public void restore(){
		myValue = myInitValue;
	}
	
	/**
	 * Get the current value
	 * @return
	 */
	public int getCurrentValue(){
		return myValue;
	}
	
	/**
	 * Set the value by parameters
	 * @param value
	 * @param args
	 */
	public void setValue(int value, Object ... args){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, args);
		myMap.put(condition, value);
	}
	
	/**
	 * 
	 * @param info
	 * @param victim
	 * @param hitter
	 */
	public void update(String info, GameObject victim, GameObject hitter){
		String condition = info + SaladConstants.SEPARATOR + victim.colid + 
				SaladConstants.SEPARATOR + hitter.colid;
		if(myMap.get(condition) == null) return;
		myValue += myMap.get(condition);
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
