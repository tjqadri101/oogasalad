package util;

import java.util.List;

import saladConstants.SaladConstants;
/**
 * Called to make an attribute for saving the Game
 * @author Main Justin (Zihao) Zhang
 */
public class AttributeMaker {
	
	/**
	 * Add attribute of the format as: key, type, parameters
	 * @param String key
	 * @param String type
	 * @param boolean duplicated (if type is duplicated in the data format)
	 * @return String attribute 
	 */
	public static String addAttribute(String key, String type, boolean duplicated, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPARATOR + type);
		if(duplicated){ attribute.append(SaladConstants.SEPARATOR + type);}
		for(Object o: params){ attribute.append(SaladConstants.SEPARATOR + o.toString());}
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, int parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if the second type is duplicated in the data format)
	 * @param list of object parameters
	 * @return String attribute
	 */
	public static String addAttribute(String key, String firstType, int firstParameter, String secondType, boolean duplicated, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPARATOR + firstType + SaladConstants.SEPARATOR + firstParameter + SaladConstants.SEPARATOR + secondType);
		if(duplicated){ attribute.append(SaladConstants.SEPARATOR + secondType); }
		for(Object o: params){ attribute.append(SaladConstants.SEPARATOR + o.toString()); }
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, String parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if the second type is duplicated in the data format)
	 * @param list of object parameters
	 * @return String attribute
	 */
	public static String addAttribute(String key, String firstType, String firstParameter, String secondType, boolean duplicated, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPARATOR + firstType + SaladConstants.SEPARATOR + firstParameter + SaladConstants.SEPARATOR + secondType);
		if(duplicated){ attribute.append(SaladConstants.SEPARATOR + secondType); }
		for(Object o: params){ attribute.append(SaladConstants.SEPARATOR + o.toString()); }
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, double parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if the second type is duplicated in the data format)
	 * @param list of object parameters
	 * @return String attribute
	 */
	public static String addAttribute(String key, String firstType, double firstParameter, String secondType, boolean duplicated, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPARATOR + firstType + SaladConstants.SEPARATOR + firstParameter + SaladConstants.SEPARATOR + secondType);
		if(duplicated){ attribute.append(SaladConstants.SEPARATOR + secondType); }
		for(Object o: params){ attribute.append(SaladConstants.SEPARATOR + o.toString()); }
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, int parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if the second type is duplicated in the data format)
	 * @param list of object parameters
	 * @return String attribute
	 */
	public static String addAttribute(String key, String firstType, int firstParameter, String secondType, boolean duplicated, Object ... args){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPARATOR + firstType + SaladConstants.SEPARATOR + firstParameter + SaladConstants.SEPARATOR + secondType);
		if(duplicated){ attribute.append(SaladConstants.SEPARATOR + secondType); }
		for(Object o: args){ attribute.append(SaladConstants.SEPARATOR + o.toString()); }
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, int parameter
	 * @param key
	 * @param type
	 * @param parameter
	 * @return String attribute
	 */
	public static String addAttribute(String key, String type, int parameter){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPARATOR + type + SaladConstants.SEPARATOR + parameter);
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, double parameter
	 * @param key
	 * @param type
	 * @param parameter
	 * @return String attribute
	 */
	public static String addAttribute(String key, String type, double parameter){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPARATOR + type + SaladConstants.SEPARATOR + parameter);
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, String parameter
	 * @param key
	 * @param type
	 * @param parameter
	 * @return String attribute
	 */
	public static String addAttribute(String key, String type, String parameter){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPARATOR + type + SaladConstants.SEPARATOR + parameter);
		return attribute.toString();
	}
	
}
