package engineManagers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import objects.SideDetector;
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
		System.out.println();
		System.out.println("**Statistics Manager setValue: " + value);
		SaladUtil.printObjectList(SaladUtil.convertArgsToObjectList(args));
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, args);
		myMap.put(condition, value);
	}
	
	protected int checkIfSideDetectorColid (GameObject object){
		if (object instanceof SideDetector){
			SideDetector detector = (SideDetector) object;
			System.out.println("isSideDtector: " + detector.getParentColid());
			return detector.getParentColid();
		}
//		System.out.println("notSideDetector " + object.getClass());
		return object.colid;
	}
	
	public abstract void update(String info, GameObject victim, GameObject hitter);
	
	public abstract void update(String info, GameObject victim, int tilecid);

	public abstract List<String> getAttributes();

}
