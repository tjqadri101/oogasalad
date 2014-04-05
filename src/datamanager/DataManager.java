
package datamanager;

import java.util.*;
import jgame.JGObject;

public class DataManager {
    private String order = "m3 attribute1 attribute2 attribute3 l1 s3";
    private static Map<String, Level> levelSet = new HashMap<String, Level>();
    private static Map<String, Scene> sceneSet = new HashMap<String, Scene>();
    private static Map<String, JGObject> objectSet = new HashMap<String, JGObject>();
    // Note to David: to set Map of those in respective level, scene 
    // this implementation requires GAE editing to be scene specific 
    
    
    private static Boolean isAuthoring = true;
    private Level currentLevel;
    private Scene currentScene;
    
    public void creatOrModify(String order){
        String objectID = "fakeID"; // the ID obtained after sub-parsing 
        if (currentLevel.getScenes.getObjects.contains(objectID)){
            create(order);
        }
        else 
            modify(order);
    }
    
    public void create(String order){
        // place where we invoke reflection to create class object Justin wrote
    }
    
    public void modify(String order){
        // place where we invoke reflection to create class object Justin wrote
    }
    
}
