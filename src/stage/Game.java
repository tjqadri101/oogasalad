package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engineManagers.InputManager;
import engineManagers.ScoreManager;
import engineManagers.TimerManager;
import objects.GameObject;
import objects.Player;

public class Game {
	
	public static final int DEFAULT_SCORE = 0;
	
	protected Map<Integer, Level> myLevels;
	protected Player myPlayer;
	protected int numLevels;
	protected ScoreManager myScoreManager;
	protected InputManager myInputManager;
	protected TimerManager myTimerManager;
	
	public Game(GameEngine){
		numLevels = 0;
		myLevels = new HashMap<Integer, Level>();
		myScoreManager = new ScoreManager(DEFAULT_SCORE);
		myInputManager = new InputManager();
		myTimerManager = new TimerManager();

	}
	
	public void addLevel(Level level) {
		numLevels++;
		myLevels.put(numLevels, level);
	}
	
	public void addScene(int levelID, Scene scene){
		myLevels.get(levelID).addScene(scene);
	}
	
	public void setPlayer(GameObject object){
		myPlayer = (Player)object;
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
	
	public List<List> getAttributes() {
		List<List> result = new ArrayList<List>();
		for (Integer i : myLevels.keySet() ) {
			result.add(myLevels.get(i).getAttributes());
		}
		return result;
	}
	/*
         * NEED implementation. This method will be called from Factory through reflection
         */
	public void modifyActor(){
	    // need implementation
	}
}
