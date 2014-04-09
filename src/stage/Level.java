package stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import saladConstants.SaladConstants;

/**
 * 
 * @author DavidChou, Justin Zhang
 *
 */
public class Level implements Serializable {

	protected Map<Integer, Scene> mySceneMap;
	protected int myID;

	public Level(int id) {
		myID = id;
		mySceneMap = new HashMap<Integer, Scene>(); 
	}
	
	public int getID(){ 
		return myID; 
	}

	public void addScene(int sceneID) {
		Scene scene = new Scene(sceneID);
		mySceneMap.put(sceneID, scene);
	}

	public void addObject(int sceneID, GameObject object) {
		mySceneMap.get(sceneID).addObject(object);
	}

//	public void setPlayerXY(int sceneID, int playerID, int x, int y) {
//		myScenes.get(sceneID).setPlayerXY(playerID, x, y);
//	}
	
	public GameObject getObject(int sceneID, int objectID) {
		return mySceneMap.get(sceneID).getObject(objectID);
	}

	public Scene getScene(int sceneID){
		return mySceneMap.get(sceneID);
	}

	public void removeScene(int sceneID) {
		mySceneMap.remove(sceneID);
	}
	
	public List<GameObject> getObjectsByColid(int colid){
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int sceneID: mySceneMap.keySet()){
			Scene scene = mySceneMap.get(sceneID);
			objects.addAll(scene.getObjectsByColid(colid));
		}
		return objects;
	}

	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_LEVEL + ",ID," + myID);
//		answer.add(SaladConstants.SWITCH_LEVEL + ",ID," + myID);
		answer.addAll(getAttributes());
		return answer;
	}
	
	public List<Scene> getScenes() {
		List<Scene> answer = new ArrayList<Scene>();
		for(int sceneID: mySceneMap.keySet()){
			answer.add(mySceneMap.get(sceneID));
		}
		return answer;
	}

	public void setScenes(List<Scene> scenes){
		for(Scene scene: scenes){
			mySceneMap.put(scene.getID(), scene);
		}
	}
	
}
