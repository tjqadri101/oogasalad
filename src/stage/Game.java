package stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.Player;

public class Game {
	
	protected Map<Integer, Level> myLevels;
	protected Player myPlayer;
	protected int numLevels;
	//protected ScoreManager myScoreManager;
	//protected KeyInputManager myKeyInputManager;
	//protected TimerManager myTimerManager
	
	public Game(){
		numLevels = 0;
	}
	
	public void addLevel(Level level) {
		numLevels++;
		myLevels.put(numLevels, level);
	}
	
	public void addScene(int levelID, Scene scene){
		
	}
	
	public void addObject(int levelID, int sceneID, GameObject object){
		
	}
	
	public GameObject getGameObject(int levelID, int sceneID, int objectID){
		
	}
	
	public Scene getScene(int levelID, int sceneID){
		
	}
	
	public void removeScene(int levelID, int sceneID) {
		
	}
	
	public void removeLevel(int levelID) {
		
	}

}
