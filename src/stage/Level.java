package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
import util.SaladUtil;

/**
 * 
 * @author Justin (Zihao) Zhang, DavidChou
 *
 */
public class Level {

	protected Map<Integer, Scene> mySceneMap;
	protected int myID;
	protected String myWinBehavior;
	protected List<Object> myWinParameters;

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
		SaladUtil.printObjectList(myWinParameters); // test use
	}
	
	public String getWinBehavior(){
		return myWinBehavior;
	}
	
	public List<Object> getWinParameters(){
		return myWinParameters;
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
		answer.add(addAttributes(myWinBehavior, myWinBehavior, myWinParameters));
		for(int a: mySceneMap.keySet()){
			List<String> sceneAttribute = mySceneMap.get(a).getAttributes();
			String switchScene = SaladConstants.SWITCH_SCENE + "," + SaladConstants.ID + "," + myID + "," + SaladConstants.ID + "," + mySceneMap.get(a).getID(); 
			sceneAttribute.add(1, switchScene);
			answer.addAll(sceneAttribute);
		}
		return answer;
	}
	
	/**
	 * Add attribute for behaviors already set
	 * @param type
	 * @param typeToken: same as type
	 * @param params
	 * @return String attribute
	 */
	protected String addAttributes(String type, String typeToken, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(SaladConstants.CREATE_GOAL + "," + type + "," + typeToken);
		for(Object o: params){
			String att = o.toString();
			attribute.append("," + att);
		}
		return attribute.toString();
	}
	
	/* @Siyang: 
         * The following getter added to facilitate testing. 
         */
        public Map<Integer, Scene> getMySceneMap(){
            return mySceneMap;
        }       

}