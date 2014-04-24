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
	
	protected int myScore;
	protected int initialScore;
	protected Map<String, Integer> myScoreMap;
	
	public ScoreManager(int startScore){
		myScore = startScore;
		initialScore = startScore;
		myScoreMap = new HashMap<String, Integer>();
	}
	
	public int getCurrentScore(){
		return myScore;
	}
	
	
	
	
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.MODIFY_SCOREMANAGER + "," + SaladConstants.INITIAL_SCORE + "," + initialScore);
		return answer;
	}

}
