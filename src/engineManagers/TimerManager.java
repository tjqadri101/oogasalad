package engineManagers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author: Justin (Zihao) Zhang
 */
import saladConstants.SaladConstants;

public class TimerManager implements Serializable{
	
	protected Map<Integer, String> myTimerMap;
	
	public TimerManager(){
		myTimerMap = new HashMap<Integer, String>();
	}
	
	public void setTimetoState(int time, String state){
		myTimerMap.put(time, state);
	}
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		for(int key: myTimerMap.keySet()){
			answer.add(SaladConstants.MODIFY_TIMERMANAGER + "," + key + "," + myTimerMap.get(key));
		}
		return answer;
	}

}
