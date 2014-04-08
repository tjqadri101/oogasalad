package stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;

import objects.GameObject;
import saladConstants.SaladConstants;

/**
 * 
 * @author DavidChou
 *
 */
public class Level implements Serializable {

	@Expose protected Map<Integer, Scene> myScenes;
	@Expose protected int myID;
	@Expose protected int mySceneTotal = 0;

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
