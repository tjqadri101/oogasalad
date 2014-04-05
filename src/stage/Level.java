package stage;
import stage.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DavidChou
 *
 */
public abstract class Level {
	
	private Map<Integer, Scene> mySceneMap;
	
	private int myID;
	private int mySceneTotal = 0;
	private int myCurrentScene = 0;
	
	public Level(int hash) {
		myID = hash;
		mySceneMap = new HashMap<>();
		
	}
	
	public void addScene(Scene scene ) {
		mySceneMap.put(mySceneTotal, scene );
		mySceneTotal++;
	}
	
	public void addObject(int sceneID, Object object) {
		mySceneMap.get(sceneID).addObject(object);
	}
	
	public Map<Integer, Object> getCurrentObjects() {
		return mySceneMap.get(myCurrentScene).getObjects();
	}
	
	public Scene getScene(){
	    return mySceneMap.get(myCurrentScene);
	}
	
	
}
