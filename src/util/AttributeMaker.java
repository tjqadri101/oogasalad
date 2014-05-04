package util;

import java.util.List;

import saladConstants.SaladConstants;


/**
 * Called to make an attribute for saving the Game
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class AttributeMaker {
	
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
		String firstParam = String.valueOf(firstParameter);
		return AttributeMaker.addAttribute(key, firstType, firstParam, secondType, duplicated, params);
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
	 * Add attribute of the format as: key, type, String parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if the second type is duplicated in the data format)
	 * @param list of object parameters
	 * @return String attribute
	 */
	public static String addAttribute(String key, String firstType, String firstParameter, String secondType, boolean duplicated, Object ... args){
		List<Object> params = SaladUtil.convertArgsToObjectList(args);
		return AttributeMaker.addAttribute(key, firstType, firstParameter, secondType, duplicated, params);
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
		String firstParam = String.valueOf(firstParameter);
		return AttributeMaker.addAttribute(key, firstType, firstParam, secondType, duplicated, params);
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
		List<Object> params = SaladUtil.convertArgsToObjectList(args);
		return AttributeMaker.addAttribute(key, firstType, firstParameter, secondType, duplicated, params);
	}
	
	/**
	 * Add attribute of the format as: key, type, int parameter
	 * @param key
	 * @param type
	 * @param parameter
	 * @return String attribute
	 */
	public static String addAttribute(String key, String type, int parameter){
		String param = String.valueOf(parameter);
		return AttributeMaker.addAttribute(key, type, param);
	}
	
	/**
	 * Add attribute of the format as: key, type, double parameter
	 * @param key
	 * @param type
	 * @param parameter
	 * @return String attribute
	 */
	public static String addAttribute(String key, String type, double parameter){
		String param = String.valueOf(parameter);
		return AttributeMaker.addAttribute(key, type, param);
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

	/**
	 * Add attribute of the format as: key, type, boolean parameter
	 * @param key
	 * @param type
	 * @param parameter
	 * @return String attribute
	 */
	public static String addAttribute(String key, String type, boolean parameter){
		String param = String.valueOf(parameter);
		return AttributeMaker.addAttribute(key, type, param);
	}
	
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
	 * Add attribute of the format as: key, type, parameters
	 * @param String key
	 * @param String type
	 * @param boolean duplicated (if type is duplicated in the data format)
	 * @return String attribute 
	 */
	public static String addAttribute(String key, String type, boolean duplicated, Object ... args){
		return AttributeMaker.addAttribute(key, type, false, SaladUtil.convertArgsToObjectList(args));
	}
}
