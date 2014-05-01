package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import engineManagers.BloodManager;
import engineManagers.CollisionManager;
import engineManagers.InputManager;
import engineManagers.LiveManager;
import engineManagers.RevivalManager;
import engineManagers.ScoreManager;
import engineManagers.SoundManager;
import engineManagers.TriggerEventManager;
import objects.GameObject;
import objects.Gravity;
import objects.NonPlayer;
import objects.Player;
import saladConstants.SaladConstants;
import util.AttributeMaker;
/**
 * A data structure that holds all the information about a game
 * @author Main Justin (Zihao) Zhang
 * @contribution David Chou
 * @contribution (tile map) Isaac (Shenghan) Chen
 */
public class Game {

    public static final String DEFAULT_NAME = "Game";

    protected Map<Integer, Level> myLevelMap;
    protected Map<String, Transition> myTransitionStateMap;
    protected ScoreManager myScoreManager;
    protected BloodManager myBloodManager;
    protected LiveManager myLiveManager;
    protected TriggerEventManager myTriggerManager;
    protected RevivalManager myRevivalManager;
    protected InputManager myInputManager;
    protected Map<Integer, Player> myPlayerMap;
    protected Gravity myGravity;
    protected CollisionManager myCollisionManager;
//    protected SoundManager mySoundManager;
    protected Map<Character, String> myTileImageMap;
    protected String myName;

    public Game(){
        initTransitionStateMap();
        myTileImageMap = new HashMap<Character, String>();
        myLevelMap = new HashMap<Integer, Level>();
        myPlayerMap = new HashMap<Integer, Player>();
        myScoreManager = new ScoreManager();
        myBloodManager = new BloodManager();
        myLiveManager = new LiveManager();
        myRevivalManager = new RevivalManager();
        myInputManager = new InputManager();
        myGravity = new Gravity();
        myCollisionManager = new CollisionManager();
        myTriggerManager = new TriggerEventManager();
//        mySoundManager = new SoundManager();
        myName = DEFAULT_NAME;

    }

    /**
     * @param the level ID that you want to add
     * @return nothing
     */
    public void addLevel(int levelID) {
        Level level = new Level(levelID);
        myLevelMap.put(levelID, level);
//        mySoundManager.playClip("Succeed");
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
     * Get a list of tile collision IDs
     * @return a list of Characters
     */
    public List<Character> getOccupiedTileColids(){
        List<Character> answer = new ArrayList<Character>();
        for(char c: myTileImageMap.keySet()){
            if(c != 0) answer.add(c);
        }
        return answer;
    }

    /**
     * Set the name of the Game
     * @param name
     */
    public void setName(String name){
        System.out.println("Game: " + "setName called" );
        myName = name;
    }

    /**
     * Get the current name of Game
     * @return name
     */
    public String getName(){
        return myName;
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
        if(scene == null) return;
        removeScene(currentLevelID, sceneID);
        Level level = myLevelMap.get(newLevelID);
        if(level != null) level.addScene(sceneID, scene);
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
        for(int id: myPlayerMap.keySet()){
        	if(myPlayerMap.get(id).colid == colid)
        		objects.add(getPlayer(id));
        }
        return objects;
    }

    /**
     * Called to add a Player for the Game
     * @param Player Object
     * @return nothing
     */
    public void setPlayer(Player player){
        myPlayerMap.put(player.getID(), player);
        myLiveManager.addPlayer(player);
    }

    /** 
     * Called to get the Player from the Game
     * @param playerID
     * @return Player Object
     */
    public Player getPlayer(int playerID){
        return myPlayerMap.get(playerID);
    }

    /**
     * Get a list of all the current players
     * @return list of players
     */
    public List<Player> getAllPlayers(){
        List<Player> players = new ArrayList<Player>();
        for(Player p: myPlayerMap.values()){
            players.add(p);
        }
        return players;
    }


    /**
     * Called to remove a Player matched to the playerID from the Game
     * @param playerID
     */
    public void deletePlayer(int playerID){
        myPlayerMap.remove(playerID);
    }

    /**
     * Called to get the transition state from the Game
     * @return a Transition
     */
    public Transition getTransitionState(String gameState){
        return myTransitionStateMap.get(gameState);
    }

    /**
     * Get gravity for the engine to apply the force
     * @return Gravity
     */
    public Gravity getGravity(){
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
     * Get the Live Manager of the Game
     * @return LiveManager
     */
    public LiveManager getLiveManager(){
        return myLiveManager;
    }
    
    /**
     * Get the Input Manager of the Game
     * @return InputManager
     */
    public InputManager getInputManager(){
        return myInputManager;
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
     * @return a list of Objects that matched with a specific Data Format
     */
    public List<String> getAttributes() {
        List <String> answer = new ArrayList<String>();
        answer.addAll(myCollisionManager.getAttributes());
        
        for (Entry<Character, String> entry : myTileImageMap.entrySet()) { // need check
            Character cid = entry.getKey();
            String imgfile = entry.getValue();
            answer.add(AttributeMaker.addAttribute(SaladConstants.SET_DRAG_TILE, SaladConstants.TILE_COLID, cid.toString(), 
            		SaladConstants.DRAG_IMAGE, false, imgfile));
        }
        for (int playerID: myPlayerMap.keySet()){
            answer.addAll(myPlayerMap.get(playerID).getAttributes());
        }
        
        for(Integer key: myLevelMap.keySet()){
            answer.addAll(myLevelMap.get(key).getAttributes()); 
        }
        for(Transition value: myTransitionStateMap.values()){
            answer.addAll(value.getAttributes()); 
        } // need check if before level or after
        
        answer.addAll(myLiveManager.getAttributes());
        answer.addAll(myBloodManager.getAttributes());
        answer.addAll(myTriggerManager.getAttributes());
        answer.addAll(myInputManager.getAttributes());
        answer.addAll(myScoreManager.getAttributes());
        answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_GAME, SaladConstants.SET_NAME, myName));
        answer.add(myGravity.getAttributes());
        return answer;
    }

    protected void initTransitionStateMap() {
        myTransitionStateMap = new HashMap<String, Transition>();
        for (String gameState: Transition.TRANSITION_STATE){
            myTransitionStateMap.put(gameState, new Transition(gameState));
        }
    }

    public void defineTileImage(char cid, String imgfile){
        myTileImageMap.put(cid, imgfile);
    }


    /** Should only be called from Engine
     * @return the only instance of TriggerEventManager
     */
    public TriggerEventManager getTriggerManager () {
        return myTriggerManager;
    }

    public RevivalManager getRevivalManager() {
        return myRevivalManager;
    }       
    
//    public SoundManager getSoundManager(){
//        return mySoundManager;
//    }

}
