package util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 * Intended for use of some general methods
 *
 */
public class Util {
	
	/**
	 * Convert an array of Strings to a List of Strings
	 * @param array
	 * @return
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
	 * Get a list of Strings from a properties file which maps the key to values separated by commas
	 * @param path to the properties file
	 * @param key in the properties file that maps to the values
	 * @param splitter, the character used to separate values (Strings) in the properties file for the given key
	 * @return
	 */
	public static List<String> getListFromPropertiesFile(String path, String key, String splitter){
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		String[] array = bundle.getString(key).split(splitter);
		return Util.convertStringArrayToList(array);
	}

}
