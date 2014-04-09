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
/**
 * 
 * @author DavidChou, Justin Zhang
 *
 */
public class Game {
	
	public static final int DEFAULT_SCORE = 0;
	
	protected Map<Integer, Level> myLevelMap;
	protected Player myPlayer;
	protected ScoreManager myScoreManager;
	protected InputManager myInputManager;
	protected TimerManager myTimerManager;
	
	public Game(){
		myLevelMap = new HashMap<Integer, Level>();
		myScoreManager = new ScoreManager(DEFAULT_SCORE);
		myInputManager = new InputManager();
		myTimerManager = new TimerManager();
	}
	
	public void addLevel(int levelID) {
		Level level = new Level(levelID);
		myLevelMap.put(levelID, level);
	}
	
	public void addScene(int levelID, int sceneID){
		myLevelMap.get(levelID).addScene(sceneID);
	}
	
	public void setPlayer(GameObject object){
		myPlayer = (Player)object;
	}
	
//	public void setPlayerXY(int levelID, int sceneID, int playerID, int x, int y) {
//		myLevels.get(levelID).setPlayerXY(sceneID, playerID, x, y);
//	}
	
	public void addObject(int levelID, int sceneID, GameObject object){
		myLevelMap.get(levelID).addObject(sceneID, object);
	}
	
	public GameObject getGameObject(int levelID, int sceneID, int objectID){
		return myLevelMap.get(levelID).getObject(sceneID, objectID);
	}
	
	public Scene getScene(int levelID, int sceneID){
		return myLevelMap.get(levelID).getScene(sceneID);
	}
	
	public void removeScene(int levelID, int sceneID) {
		myLevelMap.get(levelID).removeScene(sceneID);
	}
	
	public void removeLevel(int levelID) {
		myLevelMap.remove(levelID);
	}
	
	public void resetLevelID(int currentLevelID, int newLevelID) throws ResetLevelException{
		if(myLevelMap.containsKey(newLevelID)) throw new ResetLevelException();
		Level level = myLevelMap.get(currentLevelID);
		level.resetID(newLevelID);
		myLevelMap.remove(currentLevelID);
		myLevelMap.put(newLevelID, level);
	}
	
	public List<GameObject> getObjectsByColid(int colid){
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int levelID: myLevelMap.keySet()){
			Level level = myLevelMap.get(levelID);
			objects.addAll(level.getObjectsByColid(colid));
		}
		return objects;
	}
	
//	public List<String> getAttributes() {
//		List <String> answer = new ArrayList<String>();
//		answer.addAll(myScoreManager.getAttributes()); 
//		answer.addAll(myInputManager.getAttributes()); 
//		answer.addAll(myTimerManager.getAttributes()); 
//		for(Integer key: myLevels.keySet()){
//			answer.addAll(myLevels.get(key).getAttributes()); 
//		}
//		return answer;
//	}
}
