package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saladConstants.SaladConstants;
import util.SaladUtil;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class ScoreManager {
	
	public static final int DEFAULT_INITIAL_SCORE = 0;
	
	protected int myScore;
	protected int myInitialScore;
	protected int initialScore;
	protected Map<String, Integer> myScoreMap;
	
	public ScoreManager(int startScore){
		myScore = startScore;
		initialScore = startScore;
		myScoreMap = new HashMap<String, Integer>();
	}
	
	public ScoreManager(){
		this(DEFAULT_INITIAL_SCORE);
	}
	
	public void setInitialScore(int startScore){
		myInitialScore = startScore;
	}
	
	public void restore(){
		myScore = myInitialScore;
	}
	
	public int getCurrentScore(){
		return myScore;
	}
	
	public void setScore(int score, Object ... args){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPERATER, args);
		myScoreMap.put(condition, score);
	}
	
	public void updateScore(String info, int victimColid, int hitterColid){
		String condition = info + SaladConstants.SEPERATER + victimColid + 
				SaladConstants.SEPERATER + hitterColid;
		myScore += myScoreMap.get(condition);
	}
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.MODIFY_SCOREMANAGER + "," + SaladConstants.INITIAL_SCORE + "," + initialScore);
		return answer;
	}

}
