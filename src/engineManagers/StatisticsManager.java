package engineManagers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import objects.SideDetector;
import saladConstants.SaladConstants;
import util.SaladUtil;
/**
 * A super class of managers related to recording statistics
 * Examples of subclasses includes CollisionManager, BloodManager, LiveManager, ScoreManager, etc.
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class StatisticsManager { 
	
	protected Map<String, Integer> myMap;
	
	protected StatisticsManager(){
		myMap = new HashMap<String, Integer>();
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
	 * Do not call this method directly
	 * Used to convert an object's colid to its parent's colid if the object is a side detector
	 * @param object
	 * @return
	 */
	protected int checkIfSideDetectorColid (GameObject object){
		if (object instanceof SideDetector){
			SideDetector detector = (SideDetector) object;
			return detector.getParentColid();
		}
		return object.colid;
	}
	
	/**
	 * Called to update the value of a condition between two objects
	 * @param info
	 * @param victim
	 * @param hitter
	 */
	public abstract void update(String info, GameObject victim, GameObject hitter);
	
	/**
	 * Called to update the value of a condition between an object and a tile
	 * @param info
	 * @param victim
	 * @param tilecid
	 */
	public abstract void update(String info, GameObject victim, int tilecid);

	/**
	 * Called to get the attribute of the manager
	 * @return List of Strings
	 */
	public abstract List<String> getAttributes();

}
