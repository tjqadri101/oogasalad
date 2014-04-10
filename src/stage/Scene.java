package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import saladConstants.SaladConstants;

/**
 * 
 * @author DavidChou, Justin Zhang
 */


public class Scene {
	
	private int myID;
	private String myBackground;
	private Map<Integer, GameObject> myObjectMap;
	private double initPlayerX;
	private double initPlayerY;
	
	public Scene(int id) {
		myID = id;
		myObjectMap = new HashMap<Integer, GameObject>();
	}
		
	public int getID(){
		return myID; 
	}
	
	public void addObject(GameObject object) {
		myObjectMap.put(object.getID(), object );
	}
	
	public void setPlayerInitPosition(double xpos, double ypos){
		initPlayerX = xpos;
		initPlayerY = ypos;
	}
	
	public double[] getPlayerInitPosition(){
		double[] position = new double[2];
		position[0] = initPlayerX;
		position[1] = initPlayerY;
		return position;
	}
	
	/*
	 * Called by GameEngine to display the GameObjects
	 */
	public List<GameObject> getGameObjects() {
		List<GameObject> answer = new ArrayList<GameObject>();
		for(int id: myObjectMap.keySet()){
			answer.add(myObjectMap.get(id));
		}
		return answer;
	}
//	
//	public void setObjects(List<GameObject> gameObjects){
//		for(GameObject object: gameObjects){
//			addObject(object);
//		}
//	}
	
	//need check
	public void setBackgroundImage(String imageName) {
		myBackground = imageName;
	}
	
	public String getBackgroundImage() {
		return myBackground;
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
		for(int a: myObjectMap.keySet()){
			answer.addAll(myObjectMap.get(a).getAttributes());
		}
		answer.add(SaladConstants.MODIFY_SCENE + ",ID," + myID + ",PlayerInitialPosition," + initPlayerX + "," + initPlayerY);
		return answer;
	}
}
