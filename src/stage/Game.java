package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engineManagers.BloodManager;
import engineManagers.CollisionManager;
import engineManagers.InputManager;
import engineManagers.ScoreManager;
import engineManagers.TimerManager;
import engineManagers.TriggerEventManager;
import objects.GameObject;
import objects.Gravity;
import objects.NonPlayer;
import objects.Player;
import stage.Transition.StateType;
/**
 * A data structure that holds all the information about a game
 * @author Main Justin (Zihao) Zhang
 * @Contribution David Chou
 * @help Isaac (Shenghan) Chen
 */
public class Game {

	public static final int NONUSE_ID = 0;

	protected Map<Integer, Level> myLevelMap;
	protected Map<StateType, Transition> myNonLevelSceneMap;
	protected ScoreManager myScoreManager;
	protected BloodManager myBloodManager;
	protected TriggerEventManager myTriggerManager;
//	protected InputManager myInputManager;
//	protected TimerManager myTimerManager;
	protected Player myPlayer;
    protected Gravity myGravity;
    protected CollisionManager myCollisionManager;
    protected TriggerEventManager myTEM;


	public Game(){
		myLevelMap = new HashMap<Integer, Level>();
		myNonLevelSceneMap = new HashMap<StateType, Transition>();
		myScoreManager = new ScoreManager();
		myBloodManager = new BloodManager();
//		myInputManager = new InputManager();
//		myTimerManager = new TimerManager();
    	myGravity = new Gravity();
    	myCollisionManager = new CollisionManager();
    	myTEM = new TriggerEventManager();
	}

	/**
	 * @param the level ID that you want to add
	 * @return nothing
	 */
	public void addLevel(int levelID) {
		Level level = new Level(levelID);
//                level.register(etm);
//                etm.setSubject(level);
		myLevelMap.put(levelID, level);
	}

	/**
	 * Called to add a new scene to a particular level 
	 * @param the level ID that the new scene belongs to 
	 * @param the new scene ID
	 * @return nothing
	 */
	public void addScene(int levelID, int sceneID){
		myLevelMap.get(levelID).addScene(sceneID);
	}
	
	/**
	 * Called to add a new NonPlayer to a particular scene of a particular level
	 * @param the level ID that the new Game Object belongs to 
	 * @param the new scene ID that the new Game Object belongs to
	 * @param the new Game Object
	 * @return nothing
	 */
	public void addNonPlayer(int levelID, int sceneID, NonPlayer object){
		myLevelMap.get(levelID).addNonPlayer(sceneID, object);
	}

	/**
	 * Called to get an existing Game Object from a particular scene of a particular level
	 * @param the level ID that the Game Object belongs to 
	 * @param the scene ID that the Game Object belongs to
	 * @param the object ID
	 * @return the Game Object that matched with the input IDs
	 */
	public NonPlayer getNonPlayer(int levelID, int sceneID, int objectID){
		return myLevelMap.get(levelID).getNonPlayer(sceneID, objectID);
	}

	/**
	 * Called to get an existing scene from a particular level
	 * @param the level ID that the scene belongs to 
	 * @param the scene ID
	 * @return the scene that matched with the input IDs
	 */
	public Scene getScene(int levelID, int sceneID){
		return myLevelMap.get(levelID).getScene(sceneID);
	}
	
	/**
	 * Get an existing level by ID
	 * @param levelID
	 * @return Level
	 */
	public Level getLevel(int levelID){
		return myLevelMap.get(levelID);
	}

	/**
	 * Called to remove an existing scene from a particular level
	 * @param the level ID that the scene belongs to 
	 * @param the scene ID
	 * @return nothing
	 */
	public void removeScene(int levelID, int sceneID) {
		myLevelMap.get(levelID).removeScene(sceneID);
	}

	/**
	 * Called to remove an existing level 
	 * @param the level ID 
	 * @return nothing
	 */
	public void removeLevel(int levelID) {
		myLevelMap.remove(levelID);
	}

	/**
	 * Called to reset a new level ID to an existing level if the new level ID has not been used
	 * @param the current level ID of the level
	 * @param the new level ID 
	 * @return nothing
	 */
	public void resetLevelID(int currentLevelID, int newLevelID) {
//		if(myLevelMap.containsKey(newLevelID)) throw new ResetLevelException();
		if(myLevelMap.containsKey(newLevelID)) return;
		Level level = myLevelMap.get(currentLevelID);
		level.resetID(newLevelID);
		myLevelMap.remove(currentLevelID);
		myLevelMap.put(newLevelID, level);
	}
	
	/**
	 * Called to switch an existing scene from a level to another existing level 
	 * @param the current level ID which the scene belongs to
	 * @param the new level ID which the scene is going to belong to
	 * @param the scene ID
	 * @return nothing
	 */
	public void switchSceneToLevel(int currentLevelID, int newLevelID, int sceneID){
		Scene scene = getScene(currentLevelID, sceneID);
		removeScene(currentLevelID, sceneID);
		Level level = myLevelMap.get(newLevelID);
		level.addScene(sceneID, scene);
	}

	/**
	 * Called to get Game Objects (including Player) that matched with a certain collision ID from the whole game
	 * @param the collision ID
	 * @return a list of Game Objects
	 */
	public List<GameObject> getObjectsByColid(int colid){
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int levelID: myLevelMap.keySet()){
			Level level = myLevelMap.get(levelID);
			objects.addAll(level.getObjectsByColid(colid));
		}
		if(getPlayer(NONUSE_ID) != null && getPlayer(NONUSE_ID).colid == colid) objects.add(getPlayer(NONUSE_ID));
		return objects;
	}
	
	/**
	 * Called to set the Player for the Game
	 * @param Player Object
	 * @return nothing
	 */
	public void setPlayer(Player player){
		myPlayer = player;
	}
	
	/** 
     * Called to get the Player from the Game
     * Parameters needed but not used to facilitate GameFactory for Reflection
     * @param levelID, sceneID, objectID
     * @return Player Object
     */
    public Player getPlayer(int playerID){
    	return myPlayer;
    }

	/**
	 * Called to add the non-level transition scenes to the Game
	 * @param StateType
	 * @return void
	 */
	public void addNonLevelScene(StateType type){
		myNonLevelSceneMap.put(type, new Transition(type));
	}
	
	/**
	 * Called to get the non-level transition from the Game
	 * @return a Transition
	 */
	public Transition getNonLevelScene(StateType type){
		return myNonLevelSceneMap.get(type);
	}
    
    /**
	 * Get gravity for the engine to apply the force
	 * @return Gravity
	 */
    public Gravity getGravity(Object ... args){
    	return myGravity;
    }
    
    /**
     * Get the collision manager for the Game
     * @return CollisionManager
     */
    public CollisionManager getCollisionManager(){
    	return myCollisionManager;
    }
    
    /**
     * Get the score manager of the Game
     * @return ScoreManager
     */
    public ScoreManager getScoreManager(){
    	return myScoreManager;
    }
    
    /**
     * Get the blood manager of the Game
     * @return BloodManager
     */
    public BloodManager getBloodManager(){
    	return myBloodManager;
    }
    
    /**
	 * Called to delete an existing Game Object from a particular scene of a particular level
	 * @param the level ID that the Game Object belongs to 
	 * @param the scene ID that the Game Object belongs to
	 * @param the object ID
	 */
    public void deleteNonPlayer(int levelID, int sceneID, int objectID){
    	getScene(levelID, sceneID).deleteNonPlayer(objectID);
    }
	
	/**
	 * Called to get the Attributes of the whole Game
	 * @return a list of Objects that matched with the GAE Data Format
	 */
	public List<String> getAttributes() {
		List <String> answer = new ArrayList<String>();
//		answer.addAll(myScoreManager.getAttributes()); 
//		answer.addAll(myInputManager.getAttributes()); 
//		answer.addAll(myTimerManager.getAttributes()); 
		answer.add(myGravity.getAttributes());
		if(getPlayer(NONUSE_ID) != null){
			answer.addAll(getPlayer(NONUSE_ID).getAttributes());	
		}
		for(Integer key: myLevelMap.keySet()){
			answer.addAll(myLevelMap.get(key).getAttributes()); 
		}
		answer.addAll(myCollisionManager.getAttributes());
		for(Transition value: myNonLevelSceneMap.values()){
			answer.addAll(value.getAttributes()); 
		} // need check if before level or after
		return answer;
	}
	
	/** Should only be called from Engine
	 * @return the only instance of TriggerEventManager
	 */
	public TriggerEventManager getTEM(){
	    return myTEM;
	}
	
	/* @Siyang: 
	 * The following getter added to facilitate testing. 
	 */
	public Map<Integer, Level> getMyLevelMap(){
	    return myLevelMap;
	}	

}
