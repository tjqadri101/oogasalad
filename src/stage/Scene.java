package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.GameEngine;

import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;

/**
 * 
 * @author Justin (Zihao) Zhang, DavidChou
 */

public class Scene {
	
	public static final double DEFAULT_PLAYER_X = 0;
	public static final double DEFAULT_PLAYER_Y = 0;
	
	protected int myID;
	protected String myBackground;
	protected Map<Integer, NonPlayer> myObjectMap;
	protected double initPlayerX;
	protected double initPlayerY;
	protected int myXSize;
	protected int myYSize;
	protected Map<Integer, String> myTileImageMap;
	protected String[] myTiles;
	
	public Scene(int id) {
		myID = id;
		initPlayerX = DEFAULT_PLAYER_X;
		initPlayerY = DEFAULT_PLAYER_Y;
		myObjectMap = new HashMap<Integer, NonPlayer>();
		initTiles();
	}
	
	public Map<Integer, String> getTileImageMap(){
		return myTileImageMap;
	}
	
	public String[] getTiles(){
		return myTiles;
	}
	
	protected void initTiles(){
		int width = GameEngine.CANVAS_WIDTH;
		int height = GameEngine.CANVAS_HEIGHT;
		String temp = "";
    	for(int i=0;i<width;i++){
    		temp += 0;
    	}
    	String[] array = new String[height];
    	for(int j=0;j<height;j++){
    		array[j] = temp;
    	}
		myTiles = array;
		myTileImageMap = new HashMap<Integer, String>();
		myTileImageMap.put(0, "null");
	}
	
	public void resizeTiles(int xsize, int ysize){
		String empty_line = "";
		String suffix = "";
		int suffix_length = Math.max(xsize-getXSize(),0);
    	for(int i=0;i<xsize;i++){
    		empty_line += 0;
    		if(i==suffix_length) suffix=empty_line;
    	}
		String[] array = new String[ysize];
		for (int j=0;j<Math.min(ysize,getYSize());j++){
			array[j] = myTiles[j].substring(0,Math.min(xsize,getXSize()))+suffix;
		}
		for (int j=Math.min(ysize,getYSize());j<ysize;j++){
			array[j] = empty_line;
		}
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
		answer.add(SaladConstants.CREATE_SCENE + "," + SaladConstants.ID + "," + myID);
		answer.add(SaladConstants.MODIFY_SCENE + "," + SaladConstants.ID + "," + myID + "," + SaladConstants.BACKGROUND + "," + myBackground);
		answer.add(SaladConstants.MODIFY_SCENE + "," + SaladConstants.ID + "," + myID + "," + SaladConstants.PLAYER_INITIAL_POSITION + "," + initPlayerX + "," + initPlayerY);
		for(int a: myObjectMap.keySet()){
			answer.addAll(myObjectMap.get(a).getAttributes());
		}
		return answer;
	}
}
