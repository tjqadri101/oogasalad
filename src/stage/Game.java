package stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
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
		myLevels.get(levelID).addScene(scene);
	}
	
	public void addObject(int levelID, int sceneID, GameObject object){
		myLevels.get(levelID).addObject(sceneID, object);
	}
	
	public GameObject getGameObject(int levelID, int sceneID, int objectID){
		return myLevels.get(levelID).getObject(sceneID, objectID);
	}
	
	public Scene getScene(int levelID, int sceneID){
		return myLevels.get(levelID).getScene(sceneID);
	}
	
	public void removeScene(int levelID, int sceneID) {
		myLevels.get(levelID).removeScene(sceneID);
	}
	
	public void removeLevel(int levelID) {
		myLevels.remove(levelID);
	}
	
	public void resetLevelID(int initialLevelID, int newLevelID) {
		
	}
	
	public Map<Integer, Level> getAttributes() {
		return myLevels;
	}

}
