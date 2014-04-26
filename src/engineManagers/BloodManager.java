package engineManagers;

import java.util.HashMap;
import java.util.Map;

import saladConstants.SaladConstants;
import util.SaladUtil;
/**
 * Manage the blood of a Game Object
 * For Player, manage the blood for its each life
 * @author Main Justin (Zihao) Zhang
 *
 */
public class BloodManager {
	public static final int NOT_USED_BLOOD = 0;
	
	protected Map<String, Integer> myBloodMap;
	
	public BloodManager(){
		myBloodMap = new HashMap<String, Integer>();
	}
	
	public void setBlood(int blood, Object ... args){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, args);
		myBloodMap.put(condition, blood);
	}
	
	public int getChangeOfBlood(String info, int victimColid, int hitterColid){
		String condition = info + SaladConstants.SEPARATOR + victimColid + 
				SaladConstants.SEPARATOR + hitterColid;
		if(!myBloodMap.containsKey(condition)) return NOT_USED_BLOOD;
		return myBloodMap.get(condition);
	}

}
