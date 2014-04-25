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
 * 
 * @author Justin (Zihao) Zhang
 * @Contribution David Chou, Steve (Siyang) Wang
 */
public class Level {

	protected Map<Integer, Scene> mySceneMap;
	protected int myID;
	protected String myWinBehavior;
	protected List<Object> myWinParameters;
    protected String myEventBehavior;
    private ArrayList<Object> myEventParameters;

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
	
	public void setWinBehavior(String type, Object ... args){
		myWinBehavior = type;
		myWinParameters = new ArrayList<Object>();
		for(int i = 0; i < args.length; i ++){
			myWinParameters.add(args[i]);
		}
	}
	
	public String getWinBehavior(){
		return myWinBehavior;
	}
	
	public List<Object> getWinParameters(){
		return myWinParameters;
	}
	// following similar to teh winBehavior consider refactoring 
	public void setEventBehavior(String type, Object ... args){
	    myEventBehavior = type;
	    myEventParameters = new ArrayList<Object>();
	    for(int i = 0; i < args.length; i ++){
	        myEventParameters.add(args[i]);
	    }
	}

        public String getEventBehavior () {
                return myEventBehavior;
        }       
        
        public List<Object> getEventParameters(){
            return myEventParameters;
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
		for(int a: mySceneMap.keySet()){
			List<String> sceneAttribute = mySceneMap.get(a).getAttributes();
			String attribute = AttributeMaker.addAttribute(SaladConstants.SWITCH_SCENE, SaladConstants.ID, myID, SaladConstants.ID, false, mySceneMap.get(a).getID()); 
			sceneAttribute.add(1, attribute); 
			answer.addAll(sceneAttribute);
		}
		answer.add(AttributeMaker.addAttribute(SaladConstants.CREATE_GOAL, myWinBehavior, true, myWinParameters));
		return answer;
	}
	
	/* @Siyang: 
         * The following getter added to facilitate testing. 
         */
        public Map<Integer, Scene> getMySceneMap(){
            return mySceneMap;
        }


}