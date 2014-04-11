package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import saladConstants.SaladConstants;

/**
 * 
 * @author Justin (Zihao) Zhang, DavidChou
 *
 */
public class Level {

	protected Map<Integer, Scene> mySceneMap;
	protected int myID;

	public Level(int id) {
		myID = id;
		mySceneMap = new HashMap<Integer, Scene>(); 
	}

	public int getID(){ 
		return myID; 
	}

	public void resetID(int id){
		myID = id;
	}

	public void addScene(int sceneID) {
		Scene scene = new Scene(sceneID);
		mySceneMap.put(sceneID, scene);
	}
	
	public void addScene(int sceneID, Scene scene){
		mySceneMap.put(sceneID, scene);
	}

	public void addObject(int sceneID, GameObject object) {
		mySceneMap.get(sceneID).addObject(object);
	}

	public NonPlayer getNonPlayer(int sceneID, int objectID) {
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
		answer.add(SaladConstants.CREATE_LEVEL + "," + SaladConstants.ID + "," + myID);
		answer.add(SaladConstants.SWITCH_LEVEL + "," + SaladConstants.ID + "," + myID);
		for(int a: mySceneMap.keySet()){
			answer.addAll(mySceneMap.get(a).getAttributes());
		}
		return answer;
	}

}