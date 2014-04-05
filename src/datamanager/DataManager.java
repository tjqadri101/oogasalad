package datamanager;

import java.util.*;
import java.util.logging.Level;
import jgame.JGObject;

public class DataManager {
    private String order = "m3 attribute1 attribute2 attribute3 l1 s3";
    private static Set<String> levelSet = new HashSet<String>();
    private static Set<String> sceneSet = new HashSet<String>();
    private static Set<String> objectSet = new HashSet<String>();
    
    private static Boolean isAuthoring = true;
    private String currentLevel, currentScene;
    
    public void creatOrModify(String order){
        String objectID = "fakeID"; // the ID obtained after sub-parsing 
        if (objectSet.contains(objectID)){
            create(order);
        }
        else 
            modify(order);
    }
    
    public void create(String order){
        // place where we invoke reflection to create class object Justi wrote
    }
    
    public void modify(String order){
        // place where we invoke reflection to create class object Justi wrote
    }
    
}
