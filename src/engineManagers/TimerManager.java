package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * @Author: Justin (Zihao) Zhang
 */
import saladConstants.SaladConstants;
import util.AttributeMaker;
/**
 * May not be needed
 * @author Main Justin (Zihao) Zhang
 */
public class TimerManager {
	
	protected Map<String, Integer> myTimerMap;
	
	public TimerManager(){
		myTimerMap = new HashMap<String, Integer>();
	}
	
	public void setTimetoState(String state, int time){
		myTimerMap.put(state, time);
	}
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		for(String state: myTimerMap.keySet()){
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_TIMERMANAGER, 
					state, myTimerMap.get(state)));
		}
		return answer;
	}
	
	public int getTime(String state){
		return myTimerMap.get(state);	
	}

}
