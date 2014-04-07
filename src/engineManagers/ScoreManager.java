package engineManagers;

public class ScoreManager {
	
	protected int myScore;
	
	public ScoreManager(){
		myScore = 0;
	}
	
	public int getScore(){
		return myScore;
	}
	
	public void addScore(int points){
		myScore += points;
	}

}
