package stage;
import stage.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;

/**
 * 
 * @author DavidChou
 *
 */
public abstract class Level {
	
	protected Map<Integer, Scene> myScenes;
	
	protected int myID;
	protected int mySceneTotal = 0;
	
	public Level(int hash) {
		myID = hash;
		//mySceneMap = new HashMap<>();
		
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
	
	public List<List> getAttributes() {
		List<List> result = new ArrayList<List>();
		for (Integer i : myScenes.keySet()) {
			result.add(myScenes.get(i).getAttributes());
		}
		return result;
	}
	
}
