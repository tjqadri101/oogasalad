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
	 protected Map<Integer, Scene> myScenes;
	 protected int myID;
	 protected int mySceneTotal = 0;


	public Level(int hash) {
		myID = hash;
		myScenes = new HashMap<Integer, Scene>(); 
	}
	
	public int getID(){ 
		return myID; 
	}

	public void addScene(Scene scene ) {
		myScenes.put(mySceneTotal, scene );
		mySceneTotal++;
	}

	public void addObject(int sceneID, GameObject object) {
		myScenes.get(sceneID).addObject(object);
	}

	public void setPlayerXY(int sceneID, int playerID, int x, int y) {
		myScenes.get(sceneID).setPlayerXY(playerID, x, y);
	}
	
	public GameObject getObject(int sceneID, int objectID) {
		return myScenes.get(sceneID).getObject(objectID);
	}

	public Scene getScene(int sceneID){
		return myScenes.get(sceneID);
	}

	public void removeScene(int sceneID) {
		myScenes.remove(sceneID);
	}
	
	public List<GameObject> getObjectsByColid(int colid){
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int sceneID: myScenes.keySet()){
			Scene scene = myScenes.get(sceneID);
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
//=======
	
	public Map<Integer, Map<Integer, GameObject>> getGameObjects(){ 
		Map<Integer, Map<Integer, GameObject>> levelGameObjects = new HashMap<Integer, Map<Integer, GameObject>>(); 
		for(int i=0; i<myScenes.size(); i++){
			levelGameObjects.put(myScenes.get(i).getID(), myScenes.get(i).getGameObjects()); 
		}	
		return levelGameObjects; 
	}
	
	public void setGameObjects(Map< Integer, Map<Integer, GameObject>> gameObjects){
		for(Integer SceneKeys: gameObjects.keySet()){
			
		}
	}
	
}
