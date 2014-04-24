package util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import reflection.Reflection;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 * Intended for use of some general methods involving String or Object lists
 *
 */
public class SaladUtil {

    /**
     * Convert an array of Strings to a List of Strings
     * @param array
     * @return a list of Strings
     */
    public static List<String> convertStringArrayToList(String[] array){
        List<String> answer = new ArrayList<String>();
        for(int i = 0; i < array.length; i ++){
            answer.add(array[i]);
        }
        return answer;
    }

    /**
     * Print a list of Strings separated by '/'
     * @param list
     */
    public static void printStringList(List<String> list){
        System.out.println();
        System.out.print("StringList print starts: ");
        for(String s: list){
            System.out.print("/" + s);
        }
        System.out.println("/StringList print ends.");
        System.out.println();
    }

    /**
     * Print a list of Strings separated by '/'
     * @param list
     */
    public static void printObjectList(List<Object> list){
        System.out.println();
        System.out.print("StringList print starts: ");
        for(Object s: list){
            System.out.print(s.toString() + "/");
        }
        System.out.print("//StringList print ends.");
    }

    /**
     * Get a list of Strings from a properties file which maps the key parameter to values separated by commas
     * @param path to the properties file
     * @param key in the properties file that maps to the values
     * @param splitter, the character used to separate values (Strings) in the properties file for the given key
     * @return a list of Strings
     */
    public static List<String> getListFromPropertiesFile(String path, String key, String splitter){
        ResourceBundle bundle = ResourceBundle.getBundle(path);
        String[] array = bundle.getString(key).split(splitter);
        return SaladUtil.convertStringArrayToList(array);
    }

    /**
     * General Reflection Method 
     * @param ResourceBundle
     * @param myString
     * @param methodName
     * @param parameters for constructor
     */
    public static Object behaviorReflection(ResourceBundle myBundle, String myString, List<Object> objects, String methodName, Object constructorParam){
        if(myString == null) return null;
        try{
            Object behavior = Reflection.createInstance(myBundle.getString(myString), constructorParam);
            return Reflection.callMethod(behavior, methodName, objects);	
        } catch (Exception e){
            e.printStackTrace(); // should never reach here
            return null;
        }
    }
    
    /**
     * Convert Object ... args to List of Objects
     * @param args with variable length
     * @return List of Objects
     */
    public static List<Object> convertArgsToObjectList(Object ... args){
		List<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < args.length; i ++){
			objects.add(args[i]);
		}
		return objects;
    }
    
    /**
     * Get a copy of a list of objects
     * @param List of objects
     * @return a copy list of objects
     */
    public static List<Object> copyObjectList(List<Object> objects){
    	List<Object> answer = new ArrayList<Object>();
    	if (objects == null) return null;
    	for(Object o: objects){
    		answer.add(o);
    	}
    	return answer;
    }

}
