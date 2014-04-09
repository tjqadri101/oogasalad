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
 * @author DavidChou
 *
 */
public class Level implements Serializable {

	protected Map<Integer, Scene> mySceneMap;
	protected int myID;

	public Level(int hash) {
		myID = hash;
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

//	public List<String> getAttributes() {
//		List<String> answer = new ArrayList<String>();
//		answer.add(SaladConstants.CREATE_LEVEL + ",ID," + myID);
//		answer.add(SaladConstants.SWITCH_LEVEL + ",ID," + myID);
//		answer.addAll(getSceneAttributes());
//		return answer;
//	}
//
//	public List<String> getSceneAttributes(){
//		List<String> answer = new ArrayList<String>();
	
//	public Map<Integer, Map<Integer, GameObject>> getGameObjects(){ 
//		Map<Integer, Map<Integer, GameObject>> levelGameObjects = new HashMap<Integer, Map<Integer, GameObject>>(); 
//		for(int i=0; i<myScenes.size(); i++){
//			levelGameObjects.put(myScenes.get(i).getID(), myScenes.get(i).getGameObjects()); 
//		}	
//		return levelGameObjects; 
//	}
//	
//	public void setGameObjects(Map< Integer, Map<Integer, GameObject>> gameObjects){
//		for(Integer SceneKeys: gameObjects.keySet()){
//			
//		}
//	}
	
	public List<GameObject> getGameObjects() {
		List<GameObject> answer = new ArrayList<GameObject>();
		for(int sceneID: mySceneMap.keySet()){
			answer.addAll(c)
		}
		return answer;
	}
	
	public void setObjects(List<GameObject> gameObjects){
		for(GameObject object: gameObjects){
			addObject(object);
		}
	}
	
}
