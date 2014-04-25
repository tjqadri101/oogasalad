package engineManagers;

import java.util.HashMap;
import java.util.Map;

import saladConstants.SaladConstants;
import util.SaladUtil;

public class BloodManager {
	
	protected Map<String, int[]> myBloodMap;
	
	public BloodManager(){
		myBloodMap = new HashMap<String, int[]>();
	}
	
	public void setBlood(int victimBlood, int hitterBlood, Object ... args){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPERATER, args);
		int[] param = {victimBlood, hitterBlood};
		myBloodMap.put(condition, param);
	}
	
	public int[] getChangeOfBlood(String info, int victimColid, int hitterColid){
		String condition = info + SaladConstants.SEPERATER + victimColid + 
				SaladConstants.SEPERATER + hitterColid;
		return myBloodMap.get(condition);
	}

}
