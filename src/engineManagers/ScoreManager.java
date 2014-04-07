package engineManagers;

import java.util.ArrayList;
import java.util.List;

import saladConstants.SaladConstants;

public class ScoreManager {
	
	public static final String INITIAL_SCORE = "InitialScore";
	
	protected int myScore;
	protected int initialScore;
	
	public ScoreManager(int startScore){
		myScore = startScore;
		initialScore = startScore;
	}
	
	public int getScore(){
		return myScore;
	}
	
	public void addScore(int points){
		myScore += points;
	}
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.MODIFY_SCOREMANAGER + "," + INITIAL_SCORE + "," + initialScore);
		return answer;
	}

}
