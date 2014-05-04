package engineManagers;

import java.util.ArrayList;
import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manage the score throughout a Game
 * @Author: Justin (Zihao) Zhang
 */
public class ScoreManager extends StatisticsManager{
	protected int myInitScore;
	protected int myScore;
	public static final int DEFAULT_INITIAL_SCORE = 0;
	
	public ScoreManager(int startScore){
		super();
		myInitScore = startScore;
		restore();
	}
	
	public ScoreManager(){
		this(DEFAULT_INITIAL_SCORE);
	}
	
	/**
	 * Set the initial Value
	 * @param startValue
	 */
	public void setInitValue(int startValue){
		myInitScore = startValue;
	}
	
	/**
	 * Restore to the initial value
	 */
	public void restore(){
		myScore = myInitScore;
	}
	
	/**
	 * Get the current value
	 * @return
	 */
	public int getCurrentValue(){
		return myScore;
	}
	
	/**
	 * Called to update a condition between two objects
	 * @param info
	 * @param victim
	 * @param hitter
	 */
	@Override
	public void update(String info, GameObject victim, GameObject hitter){
		int hitterColid = checkIfSideDetectorColid(hitter);
		int victimColid = checkIfSideDetectorColid(victim);
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victimColid, hitterColid);
		updateScore(condition);
	}
	
	/**
	 * Do not call this method directly
	 * @param condition
	 */
	protected void updateScore(String condition){
		if(!myMap.containsKey(condition)) return;
		myScore += myMap.get(condition);
	}
	
	/**
	 * Called to update a condition between an object and a tile
	 */
	public void update(String info, GameObject victim, int tilecid) {
		int victimColid = checkIfSideDetectorColid(victim);
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, info, victimColid, tilecid);
		updateScore(condition);
	}
	
	/**
	 * Called to update a transition to a new scene or level
	 * @param info
	 * @param oldLevelOrSceneID
	 */
	public void update(String info, int oldLevelOrSceneID){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, oldLevelOrSceneID);
		updateScore(condition);
	}
	
	/**
	 * Called to update a general condition
	 * @param condition
	 */
	public void update(String condition){
		updateScore(condition);
	}
	
	/**
	 * Get attribute
	 * @return List of Strings
	 */
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_SCOREMANAGER, 
				SaladConstants.INITIAL_SCORE, myInitScore));
		for (String condition: myMap.keySet()){
			String type = null;
			StringBuilder param = new StringBuilder();
			param.append(myMap.get(condition) + SaladConstants.SEPARATOR);
			param.append(condition);
			List<Object> params = SaladUtil.convertStringListToObjectList(SaladUtil.convertStringArrayToList(
					param.toString().split(SaladConstants.SEPARATOR)));
			if(condition.startsWith(SaladConstants.COLLISION)) type = SaladConstants.SET_COLLISION_SCORE;
			else if(condition.startsWith(SaladConstants.LEVEL_DONE) || condition.startsWith(SaladConstants.SCENE_DONE)) type = SaladConstants.SET_TRANSITION_SCORE;
			else if(condition.startsWith(SaladConstants.TIME)) type = SaladConstants.SET_SCORE_CONDITION;
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_SCOREMANAGER, type, false, params));
		}
		return answer;
	}

}
