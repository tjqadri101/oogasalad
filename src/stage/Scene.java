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
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String DEFAULT_BEHAVIOR = "scene_winning";
	private ResourceBundle myWinnables;
	
	private int myID;
	private int myObjectCounter = 0;
	private String myBackground;
	private Map<Integer, GameObject> myObjects;
	private String myWinString;
	
	public Scene(int hash) {
		myID = hash;
		myObjects = new HashMap<Integer, GameObject>(); 
		//myObjectMap = new HashMap<>();
		//myWinnables = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
	}
		
	public void addObject(GameObject object ) {
		myObjectCounter++;
		myObjects.put(myObjectCounter, (GameObject) object );
	}

	public Map<Integer, GameObject> getObjects() {
		return myObjects;
	}
	
	public void setBackgroundImage(String s) {
		myBackground = s;
	}
	
	public void setWinBehavior(String s) {
		myWinString = s;
	}
	
	public GameObject getObject(int objectID) {
		return myObjects.get(objectID);
	}
	
	
	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_SCENE + ",ID," + myID + ",Image," + myBackground);
		return answer;
	}
}
