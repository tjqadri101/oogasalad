package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import objects.GameObject;

import reflection.Reflection;

/**
 * 
 * @author DavidChou
 *
 */

public class Scene {
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String DEFAULT_BEHAVIOR = "scene_winning";
	private ResourceBundle myWinnables;
	
	private int myID;
	private int myObjectCounter = 0;
	private String myBackground;
	private Map<Integer, GameObject> myObjectMap;
	private String myWinString;
	
	public Scene(int hash) {
		myID = hash;
		//myObjectMap = new HashMap<>();
		myWinnables = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
	}
		
	public void addObject(GameObject object ) {
		myObjectCounter++;
		myObjectMap.put(myObjectCounter, (GameObject) object );
	}

	public Map<Integer, GameObject> getObjects() {
		return myObjectMap;
	}
	
	public void setBackgroundImage(String s) {
		myBackground = s;
	}
	
	public void setWinBehavior(String s) {
		myWinString = s;
	}
	
	public GameObject getObject(int objectID) {
		return myObjectMap.get(objectID);
	}
	
	
	public Map<Integer, GameObject> getAllObjects() {
		return myObjectMap;
	}
}
