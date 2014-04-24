package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saladConstants.SaladConstants;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class ScoreManager {
	public static final int DEFAULT_SCORE = 0;
	
	protected int myScore;
	protected int initialScore;
	protected Map<String, Integer> myScoreMap;
	
	public ScoreManager(int startScore){
		myScore = startScore;
		initialScore = startScore;
		myScoreMap = new HashMap<String, Integer>();
	}
	
	public ScoreManager(){
		this(DEFAULT_SCORE);
	}
	
	public int getCurrentScore(){
		return myScore;
	}
	
	public void setScore(String condition, int score){
		myScoreMap.put(condition, score);
	}
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.MODIFY_SCOREMANAGER + "," + SaladConstants.INITIAL_SCORE + "," + initialScore);
		return answer;
	}

}
