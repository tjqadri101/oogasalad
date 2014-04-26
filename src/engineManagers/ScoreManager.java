package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class ScoreManager {
	
	public static final int DEFAULT_INITIAL_SCORE = 0;
	
	protected int myScore;
	protected int myInitialScore;
	protected Map<String, Integer> myScoreMap;
	
	public ScoreManager(int startScore){
		myScore = startScore;
		myInitialScore = startScore;
		myScoreMap = new HashMap<String, Integer>();
	}
	
	public ScoreManager(){
		this(DEFAULT_INITIAL_SCORE);
	}
	
	/**
	 * Set the initial score
	 * @param startScore
	 */
	public void setInitialScore(int startScore){
		myInitialScore = startScore;
	}
	
	/**
	 * Restore to initial score
	 */
	public void restore(){
		myScore = myInitialScore;
	}
	
	/**
	 * Get the current updated score
	 * @return score
	 */
	public int getCurrentScore(){
		return myScore;
	}
	
	/**
	 * Called to set the change of score to a condition
	 * @param score
	 * @param args: condition
	 */
	public void setScore(int score, Object ... args){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPERATER, args);
		myScoreMap.put(condition, score);
	}
	
	/**
	 * Called to update the current score
	 * @param info
	 * @param victimColid
	 * @param hitterColid
	 */
	public void updateScore(String info, int victimColid, int hitterColid){
		String condition = info + SaladConstants.SEPERATER + victimColid + 
				SaladConstants.SEPERATER + hitterColid;
		if(myScoreMap.get(condition) == null) return;
		myScore += myScoreMap.get(condition);
		System.out.println("ScoreManager current score: " + myScore);
	}
	
	/**
	 * Called to update the current score
	 * @param oldLevelOrSceneID
	 * @param newLevelOrSceneID
	 */
	public void updateScore(String oldLevelOrSceneID, String newLevelOrSceneID){
		String condition = oldLevelOrSceneID + SaladConstants.SEPERATER + newLevelOrSceneID;
		if(myScoreMap.get(condition) == null) return;
		myScore += myScoreMap.get(condition);
	}
	
	/**
	 * Get attribute
	 * @return List of Strings
	 */
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_SCOREMANAGER, 
				SaladConstants.INITIAL_SCORE, myInitialScore));
		for (String condition: myScoreMap.keySet()){
			String type = null;
			StringBuilder param = new StringBuilder();
			param.append(myScoreMap.get(condition) + SaladConstants.SEPERATER);
			param.append(condition);
			List<Object> params = SaladUtil.convertStringListToObjectList(SaladUtil.convertStringArrayToList(
					param.toString().split(SaladConstants.SEPERATER)));
			if(condition.startsWith(SaladConstants.COLLISION)) type = SaladConstants.SET_COLLISION_SCORE;
			if(condition.startsWith(SaladConstants.LEVEL) || condition.startsWith(SaladConstants.SCENE)) type = SaladConstants.SET_TRANSITION_SCORE;
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_SCOREMANAGER, type, false, params));
		}
		return answer;
	}

}
