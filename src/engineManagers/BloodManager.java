package engineManagers;

import java.util.HashMap;
import java.util.Map;

import saladConstants.SaladConstants;
import util.SaladUtil;

public class BloodManager {
	public static final int NOT_USED_BLOOD = 0;
	
	protected Map<String, Integer> myBloodMap;
	
	public BloodManager(){
		myBloodMap = new HashMap<String, Integer>();
	}
	
	public void setBlood(int blood, Object ... args){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPERATER, args);
		myBloodMap.put(condition, blood);
	}
	
	public int getChangeOfBlood(String info, int victimColid, int hitterColid){
		String condition = info + SaladConstants.SEPERATER + victimColid + 
				SaladConstants.SEPERATER + hitterColid;
		if(!myBloodMap.containsKey(condition)) return NOT_USED_BLOOD;
		return myBloodMap.get(condition);
	}

}
