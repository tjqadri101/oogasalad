package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import objects.GameObject;
import reflection.Reflection;
import saladConstants.SaladConstants;

/**
 * 
 * @author DavidChou
 *
 */

public class Scene {
	
	
	private int myID;
	private int myObjectCounter = 0;
	private String myBackground;
	private Map<Integer, GameObject> myObjects;
	private String myWinString;
	
	public Scene(int hash) {
		myID = hash;
		myObjects = new HashMap<Integer, GameObject>();
	}
		
	public void addObject(GameObject object ) {
		myObjectCounter++;
		myObjects.put(myObjectCounter, (GameObject) object );
	}
	
	public void setPlayerXY(int playerID, int x, int y) {
		myObjects.get(playerID).setPos(x,y);
	}

	public Map<Integer, GameObject> getObjects() {
		return myObjects;
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
	
	public List<GameObject> getObjectsByColid(int colid){
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int objectID: myObjects.keySet()){
			GameObject object = myObjects.get(objectID);
			if(object.colid == colid) objects.add(object);
		}
		return objects;
	}
	
	
	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_SCENE + ",ID," + myID + ",Image," + myBackground);
		answer.add(SaladConstants.SWITCH_SCENE + ",ID," + myID + ",Image," + myBackground);
		return answer;
	}
}
