package stage;
import stage.*;
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
	
	protected Map<Integer, Scene> mySceneMap;
	
	protected int myID;
	protected int mySceneTotal = 0;
	
	public Level(int hash) {
		myID = hash;
		//mySceneMap = new HashMap<>();
		
	}
	
	public void addScene(Scene scene ) {
		mySceneMap.put(mySceneTotal, scene );
		mySceneTotal++;
	}
	
	public void addObject(int sceneID, Object object) {
		mySceneMap.get(sceneID).addObject(object);
	}
	
	public GameObject getObject(int sceneID, int objectID) {
		return mySceneMap.get(sceneID).getObject(objectID);
	}
	
	public Scene getScene(int sceneID){
	    return mySceneMap.get(sceneID);
	}
	
	
}
