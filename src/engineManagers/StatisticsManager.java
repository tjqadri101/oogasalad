package engineManagers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import saladConstants.SaladConstants;
import util.SaladUtil;
/**
 * 
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
	
	public abstract void update(String info, GameObject victim, GameObject hitter);

	public abstract List<String> getAttributes();

}
