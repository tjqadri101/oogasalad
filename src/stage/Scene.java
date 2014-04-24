package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.GameEngine;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
import util.AttributeMaker;

/**
 * 
 * @author Justin (Zihao) Zhang
 * @CoAuthor David Chou
 * @contribution (for tiles) Shenghan Chen
 */

public class Scene {
	public static final String DEFAULT_TILE_INFO = "null";
	
	protected int myID;
	protected String myBackground;
	protected Map<Integer, NonPlayer> myObjectMap;
	protected double initPlayerX;
	protected double initPlayerY; // tell GAE to send two orders for creating the player; one to setInitPosition, the other one to create the object
	protected int myXSize;
	protected int myYSize;
	protected Map<Integer, String> myTileImageMap;
	protected String[] myTiles;
	
	public Scene(int id) {
		myID = id;
		myObjectMap = new HashMap<Integer, NonPlayer>();
		setSize(GameEngine.CANVAS_WIDTH,GameEngine.CANVAS_HEIGHT);
		initTiles();
	}
	
	public Map<Integer, String> getTileImageMap(){
		return myTileImageMap;
	}
	
	public String[] getTiles(){
		return myTiles;
	}
	
	protected void initTiles(){
		String temp = "";
    	for(int i = 0; i < getXSize(); i ++){ temp += 0; }
    	String[] array = new String[getYSize()];
    	for(int j = 0; j < getYSize(); j ++){ array[j] = temp; }
		myTiles = array;
		myTileImageMap = new HashMap<Integer, String>();
		myTileImageMap.put(0, DEFAULT_TILE_INFO);
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
	
	public void updateTiles(int cid, int left, int top, int width, int height){
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
		myXSize = xsize;
		myYSize = ysize;
	}
	
	/**
	 * Get the x size of the object image
	 * @return int
	 */
	public int getXSize(){
		return myXSize;
	}
	
	/**
	 * Get the y size of the object image
	 * @return int
	 */
	public int getYSize(){
		return myYSize;
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
	
	public String getBackgroundImage() {
		return myBackground;
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
//		answer.add(AttributeAdder.addAttribute(SaladConstants.MODIFY_SCENE, SaladConstants.ID, myID, SaladConstants.BACKGROUND, false, myBackground));
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_SCENE, SaladConstants.ID, myID, SaladConstants.PLAYER_INITIAL_POSITION, false, initPlayerX, initPlayerY));
		for(int a: myObjectMap.keySet()){
			answer.addAll(myObjectMap.get(a).getAttributes());
		}
		return answer;
	}
	
	/*@Siyang 
	 * Public method added for testing only. 
	 * Should not call this method inside the program
	 */
	public String getBackground(){       
	    return myBackground;
	}
}
