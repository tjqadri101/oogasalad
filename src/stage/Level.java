package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
import util.AttributeMaker;

/**
 * @author Main Justin (Zihao) Zhang
 * @contribution David Chou
 */
public class Level {

	protected Map<Integer, Scene> mySceneMap;
	protected int myID;
	protected int myInitialSceneID;

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
	
	public void setInitialScene(int sceneID){
		if(mySceneMap.containsKey(sceneID))
			myInitialSceneID = sceneID;
	}
	
	public int getInitialSceneID(){
		return myInitialSceneID;
	}

	public void addScene(int sceneID) {
		Scene scene = new Scene(sceneID);
		mySceneMap.put(sceneID, scene);
	}
	
	public void addScene(int sceneID, Scene scene){
		mySceneMap.put(sceneID, scene);
	}

	public void addNonPlayer(int sceneID, NonPlayer object) {
		mySceneMap.get(sceneID).addNonPlayer(object);
	}

	public NonPlayer getNonPlayer(int sceneID, int objectID) {
		return mySceneMap.get(sceneID).getNonPlayer(objectID);
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
		answer.add(AttributeMaker.addAttribute(SaladConstants.CREATE_LEVEL, SaladConstants.ID, myID));
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_LEVEL, SaladConstants.ID, myID, 
				SaladConstants.SET_INIT_SCENE, false, myInitialSceneID));
		for(int a: mySceneMap.keySet()){
			List<String> sceneAttribute = mySceneMap.get(a).getAttributes();
			sceneAttribute.add(0, AttributeMaker.addAttribute(SaladConstants.CREATE_SCENE, 
					SaladConstants.ID, myID, SaladConstants.ID, false, mySceneMap.get(a).getID()));
			String attribute = AttributeMaker.addAttribute(SaladConstants.SWITCH_SCENE, SaladConstants.ID, myID, SaladConstants.ID, false, mySceneMap.get(a).getID()); 
			sceneAttribute.add(1, attribute); 
			answer.addAll(sceneAttribute);
		}
		return answer;
	}

}