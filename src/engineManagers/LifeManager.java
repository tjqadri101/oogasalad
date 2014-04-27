package engineManagers;

import java.util.Map;

import saladConstants.SaladConstants;
import util.SaladUtil;
/**
 * Manage the overall lives of the Player throughout the whole Game
 * @author Main Justin (Zihao) Zhang
 *
 */
public class LifeManager {
	
	protected int myInitLives;
	protected int myLives;
	protected Map<String, Integer> myLiveMap;
	
	public LifeManager(){
		myInitLives = SaladConstants.DEFAULT_INIT_LIVES;
		myLives = myInitLives;
	}
	
	public void setInitLives(int lives){
		myInitLives = lives;
	}
	
	/**
	 * Called to set the change of score to a condition
	 * @param score
	 * @param args: condition
	 */
	public void setChangeOfLive(int lives, Object ... args){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, args);
		myLiveMap.put(condition, lives);
	}
	
	/**
	 * Called to update the current Live
	 * @param info
	 * @param victimColid
	 * @param hitterColid
	 */
	public void updateLive(String info, int victimColid, int hitterColid){
		String condition = info + SaladConstants.SEPARATOR + victimColid + 
				SaladConstants.SEPARATOR + hitterColid;
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
		String condition = oldLevelOrSceneID + SaladConstants.SEPARATOR + newLevelOrSceneID;
		if(myScoreMap.get(condition) == null) return;
		myScore += myScoreMap.get(condition);
	}
	
	/**
	 * Called check if score is incremented by a condition (i.e. time)
	 * @param condition
	 */
	public void updateScore(String condition){
		if(myScoreMap.containsKey(condition)){
			myScore += myScoreMap.get(condition);
		}
	}
	
	public int getCurrentLives(){
		return myLives;
	}

	public void updateLevelDoneLives(){
		if(myRestoreLevel){
			myLives = myInitLives;
		}
	}
}
