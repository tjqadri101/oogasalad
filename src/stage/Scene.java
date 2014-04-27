package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.Set;
import engine.GameEngine;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;

/**
 * 
 * @author Main Justin (Zihao) Zhang
 * @contribution David Chou
 * @contribution (for tiles) Shenghan Chen
 * @contribution (for trigger) Steve (Siyang) Wang
 */

public class Scene {
	public static final String DEFAULT_TILE_INFO = "null";
	
	protected String myBackground;
	protected boolean myIfWrapHorizontal;
	protected boolean myIfWrapVertical;
	
	protected int myID;
	protected double initPlayerX;
	protected double initPlayerY; // tell GAE to send two orders for creating the player; one to setInitPosition, the other one to create the object
	protected int myFieldXSize;
	protected int myFieldYSize;
	
	protected Map<Integer, NonPlayer> myObjectMap;
	protected Map<Character, String> myTileImageMap;
	protected String[] myTiles;
	
	   protected String myTrigger;
	        protected boolean myTriggerFlag;
	        protected List<Object> myTriggerParameter;
	        protected List<Object> myEventParameter;
	        protected String myEvent;
            
	
	public Scene(int id) {
		myID = id;
		myObjectMap = new HashMap<Integer, NonPlayer>();
		setSize(GameEngine.CANVAS_WIDTH,GameEngine.CANVAS_HEIGHT);
		initTiles();
	}
	
	public void defineTileImage(char cid, String imgfile){
		myTileImageMap.put(cid, imgfile);
	}
	
	public Set<Entry<Character, String>> getTileImageMap(){
		return myTileImageMap.entrySet();
	}
	
	public String[] getTiles(){
		return myTiles;
	}
	
	public void setTiles(String[] tiles){
		myTiles = tiles;
	}
	
	protected void initTiles(){
		String temp = "";
    	for(int i = 0; i < getXSize(); i ++){ temp += 0; }
    	String[] array = new String[getYSize()];
    	for(int j = 0; j < getYSize(); j ++){ array[j] = temp; }
		myTiles = array;
		myTileImageMap = new HashMap<Character, String>();
		defineTileImage('0', DEFAULT_TILE_INFO);
	}
	
	public void resizeTiles(int xsize, int ysize){
		String empty_line = "";
		String suffix = "";
		int suffix_length = Math.max(xsize-getXSize(),0);
    	for(int i=0;i<xsize;i++){
    		if(i==suffix_length) suffix=empty_line;
    		empty_line += 0;
    	}
		String[] array = new String[ysize];
		for (int j=0;j<Math.min(ysize,getYSize());j++){
			array[j] = myTiles[j].substring(0,Math.min(xsize,getXSize()))+suffix;
		}
		for (int j=Math.min(ysize,getYSize());j<ysize;j++){
			array[j] = empty_line;
		}
		myTiles = array;
	}
	
	public void updateTiles(char cid, int left, int top, int width, int height){
		String temp = "";
    	for(int i=0;i<Math.min(width,getXSize()-left);i++){
    		temp += cid;
    	}
		for (int j=top;j<Math.min(top+height,getYSize());j++){
			String str = myTiles[j];
			myTiles[j] = str.substring(0, left)+temp+str.substring(left+temp.length());
		}
	}
	
	/**
	 * Set the dimension of this scene
	 * @param xsize
	 * @param ysize
	 */
	public void setSize(int xsize, int ysize){
		myFieldXSize = xsize;
		myFieldYSize = ysize;
	}
	
	/**
	 * Get the x size of the object image
	 * @return int
	 */
	public int getXSize(){
		return myFieldXSize;
	}
	
	/**
	 * Get the y size of the object image
	 * @return int
	 */
	public int getYSize(){
		return myFieldYSize;
	}
		
	public int getID(){
		return myID; 
	}
	
	public void addNonPlayer(NonPlayer object) {
		myObjectMap.put(object.getID(), object);
	}
	
	public void setPlayerInitPosition(double xpos, double ypos){
		initPlayerX = xpos;
		initPlayerY = ypos;
	}
	/**
	 * Get the Player's initial position in this scene
	 * @return a double array[]; the first index refers to the x pos; the second index refers to the y pos
	 */
	public double[] getPlayerInitPosition(){
		double[] position = new double[2];
		position[0] = initPlayerX;
		position[1] = initPlayerY;
		return position;
	}
	
	/**
	 * Called by GameEngine to display the GameObjects
	 * @return a list of Game Objects for the current scene
	 */
	public List<GameObject> getGameObjects() {
		List<GameObject> answer = new ArrayList<GameObject>();
		for(int id: myObjectMap.keySet()){
			answer.add(myObjectMap.get(id));
		}
		return answer;
	}
	
	public void setBackgroundImage(String fileName) {
		myBackground = fileName;
	}
	
	public void setWrap(boolean ifWrapHorizontal, boolean ifWrapVertical) {
		myIfWrapHorizontal = ifWrapHorizontal;
		myIfWrapVertical = ifWrapVertical;
	}
	
	public String getBackgroundImage() {
		return myBackground;
	}
	
	public boolean ifWrapHorizontal(){
		return myIfWrapHorizontal;
	}
	
	public boolean ifWrapVertical(){
		return myIfWrapVertical;	
	}
	
	public NonPlayer getNonPlayer(int objectID) {
		return myObjectMap.get(objectID);
	}
	
	public void deleteNonPlayer(int objectID) {
		getNonPlayer(objectID).remove();
		myObjectMap.remove(objectID);
	}

	public List<GameObject> getObjectsByColid(int colid){
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int objectID: myObjectMap.keySet()){
			GameObject object = myObjectMap.get(objectID);
			if(object.colid == colid) objects.add(object);
		}
		return objects;
	}
	
	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		answer.add(AttributeMaker.addAttribute(SaladConstants.CREATE_SCENE, SaladConstants.ID, myID));
		List<Object> backgroundParams = SaladUtil.convertArgsToObjectList(myBackground, myIfWrapHorizontal, 
				myIfWrapVertical, myFieldXSize, myFieldYSize);
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_SCENE_VIEW, SaladConstants.BACKGROUND, false, backgroundParams));
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_SCENE, SaladConstants.ID, myID, SaladConstants.PLAYER_INITIAL_POSITION, false, initPlayerX, initPlayerY));
		for(int a: myObjectMap.keySet()){
			answer.addAll(myObjectMap.get(a).getAttributes());
		}
		for (Entry<Character, String> entry : getTileImageMap()) {
			Character cid = entry.getKey();
			String imgfile = entry.getValue();
			answer.add(AttributeMaker.addAttribute(SaladConstants.SET_DRAG_TILE, SaladConstants.COLLISION_ID, cid, SaladConstants.DRAG_IMAGE, false, imgfile));
		}
		String tiles = SaladConstants.CREATE_TILE + SaladConstants.SEPARATOR + SaladConstants.TILE_IMAGE;
		for (String line: getTiles()) {
			tiles += SaladConstants.SPACE + line;
		}
		answer.add(tiles);
		return answer;
	}
	
	/**
         * Used for triggerManager to inspect the trigger
         * @return Trigger
         */
        public String getTrigger(){
                return myTrigger;
        }        

        /**
         * Used for triggerManager to checkTrigger at each doFrame in engine
         * @return Trigger
         */
        public boolean checkTrigger(GameEngine myEngine){
                if (myTrigger == null) return false;
                ResourceBundle behaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.OBJECT_BEHAVIOR);
                Object answer = SaladUtil.behaviorReflection(behaviors, myTrigger, myTriggerParameter, "checkTrigger", myEngine);
                myTriggerFlag = (boolean) answer;
                return myTriggerFlag;
        }       
        
        /**
         * Used for triggerManager to inspect the trigger
         * @return Trigger
         */
        public String getEvent(){
                return myEvent;
        }  
        
        /**
         * Used for triggerManager to checkTrigger at each doFrame in engine
         * @return Trigger
         */
        public void doEvent(GameEngine myEngine){
            try{
                ResourceBundle behaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.OBJECT_BEHAVIOR);
                SaladUtil.behaviorReflection(behaviors, myEvent, myEventParameter, "doEvent", myEngine);
            }
            catch(Exception e){
                e.printStackTrace(); // shall not reach here
            }
        }
        
        /**
         * Used for to set events for specific object
         * @return Trigger
         */
        public void setTriggerAndEvent(Object ... args){
            //unimplemented
//                myTrigger
        }
	
	/*@Siyang 
	 * Public method added for testing only. 
	 * Should not call this method inside the program
	 */
	public String getBackground(){       
	    return myBackground;
	}
	
}
