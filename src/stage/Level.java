package stage;

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
public class Level {

	protected Map<Integer, Scene> myScenes;

	protected int myID;
	protected int mySceneTotal = 0;

	public Level(int hash) {
		myID = hash;
		myScenes = new HashMap<Integer, Scene>(); 
	}

	public void addScene(Scene scene ) {
		myScenes.put(mySceneTotal, scene );
		mySceneTotal++;
	}

	public void addObject(int sceneID, GameObject object) {
		myScenes.get(sceneID).addObject(object);
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

	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_LEVEL + ",ID," + myID);
		answer.add(SaladConstants.SWITCH_LEVEL + ",ID," + myID);
		answer.addAll(getSceneAttributes());
		return answer;
	}

	public List<String> getSceneAttributes(){
		List<String> answer = new ArrayList<String>();
		for(int i=0; i<myScenes.size(); i++){
			answer.addAll(myScenes.get(i).getAttributes()); 
		}
		return answer; 
	}

}
