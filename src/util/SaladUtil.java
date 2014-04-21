package util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import reflection.Reflection;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 * Intended for use of some general methods
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
            System.out.print(s + "/");
        }
        System.out.print("//StringList print ends.");
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

}
