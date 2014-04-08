package stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.annotations.Expose;

import objects.GameObject;
import reflection.Reflection;
import saladConstants.SaladConstants;

/**
 * 
 * @author DavidChou
 *
 */

public class Scene implements Serializable{
	
	
	@Expose private int myID;
	@Expose private int myObjectCounter = 0;
	@Expose private String myBackground;
	private Map<Integer, GameObject> myObjects;
	@Expose private String myWinString;
	
	public Scene(int hash) {
		myID = hash;
		myObjects = new HashMap<Integer, GameObject>();
	}
		
	public int getID(){
		return myID; 
	}
	
	public void addObject(GameObject object ) {
		myObjectCounter++;
		myObjects.put(myObjectCounter, (GameObject) object );
	}
	
	public void setPlayerXY(int playerID, int x, int y) {
		myObjects.get(playerID).setPos(x,y);
	}

	public Map<Integer, GameObject> getGameObjects() {
		return myObjects;
	}
	
	public void setObjects(Map<Integer, GameObject> gameObjects){
		myObjects= gameObjects;
	}
	
	public void setBackgroundImage(String s) {
		myBackground = s;
	}
	
	public String getBackgroundImage() {
		return myBackground;
	}
	
	public void setWinBehavior(String s) {
		myWinString = s;
	}
	
	public GameObject getObject(int objectID) {
		return myObjects.get(objectID);
	}
	
}
