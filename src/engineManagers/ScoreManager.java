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
	 * 
	 * @param info
	 * @param victim
	 * @param hitter
	 */
	@Override
	public void update(String info, GameObject victim, GameObject hitter){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victim.colid, hitter.colid);
		if(!myMap.containsKey(condition)) return;
		myScore += myMap.get(condition);
	}
	
	@Override
	public void update(String info, GameObject victim, int tilecid) {
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, info, victim.colid, tilecid);
		if(!myMap.containsKey(condition)) return;
		myScore += myMap.get(condition);
	}
	
	public void update(String info, String oldLevelOrSceneID, String newLevelOrSceneID){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, oldLevelOrSceneID, newLevelOrSceneID);
		if(myMap.get(condition) == null) return;
		myScore += myMap.get(condition);
	}
	
	public void update(String condition){
		if(myMap.containsKey(condition)){
			myScore += myMap.get(condition);
		}
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
			if(condition.startsWith(SaladConstants.LEVEL) || condition.startsWith(SaladConstants.SCENE)) type = SaladConstants.SET_TRANSITION_SCORE;
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_SCOREMANAGER, type, false, params));
		}
		return answer;
	}

}
