package stage;

import java.io.Serializable;
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
 * @author DavidChou, Justin Zhang
 */

public class Scene implements Serializable{
	
	private int myID;
	private String myBackground;
	private Map<Integer, GameObject> myObjectMap;
	private String myWinString;
	
	public Scene(int hash) {
		myID = hash;
		myObjectMap = new HashMap<Integer, GameObject>();
	}
		
	public int getID(){
		return myID; 
	}
	
	public void addObject(GameObject object) {
		myObjectMap.put(object.getID(), object );
	}
	
//	public void setPlayerXY(int playerID, int x, int y) {
//		myObjects.get(playerID).setPos(x,y);
//	}

	public List<GameObject> getGameObjects() {
		List<GameObject> answer = new ArrayList<GameObject>();
		for(int id: myObjectMap.keySet()){
			answer.add(myObjectMap.get(id));
		}
		return answer;
	}
	
	public void setObjects(List<GameObject> gameObjects){
		for(GameObject object: gameObjects){
			addObject(object);
		}
	}
	
	//need check
	public void setBackgroundImage(String imageName) {
		myBackground = imageName;
	}
	
	public String getBackgroundImage() {
		return myBackground;
	}
	
	//need check
	public void setWinBehavior(String s) {
		myWinString = s;
	}
	
	public GameObject getObject(int objectID) {
		return myObjectMap.get(objectID);
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
		answer.add(SaladConstants.CREATE_SCENE + ",ID," + myID + ",Image," + myBackground);
		answer.add(SaladConstants.SWITCH_SCENE + ",ID," + myID + ",Image," + myBackground);
		return answer;
	}
}
