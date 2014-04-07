package engineManagers;

public class ScoreManager {
	public static final int DEFAULT_SCORE = 0;
	
	protected int myScore;
	
	public ScoreManager(int initialScore){
		myScore = initialScore;
	}
	
	public ScoreManager(){
		this(DEFAULT_SCORE);
	}
	
	public int getScore(){
		return myScore;
	}
	
	public void addScore(int points){
		myScore += points;
	}

}
