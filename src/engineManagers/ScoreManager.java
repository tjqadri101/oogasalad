package engineManagers;

import java.util.ArrayList;
import java.util.List;

import saladConstants.SaladConstants;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class ScoreManager {
	
	protected int myScore;
	protected int initialScore;
	
	public ScoreManager(int startScore){
		myScore = startScore;
		initialScore = startScore;
	}
	
	public void addScore(int points){
		myScore += points;
	}
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.MODIFY_SCOREMANAGER + "," + SaladConstants.INITIAL_SCORE + "," + initialScore);
		return answer;
	}

}
