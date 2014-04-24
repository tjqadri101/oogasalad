package util;

import java.util.List;

import saladConstants.SaladConstants;

public class AttributeAdder {
	
	/**
	 * Add attribute of the format as: key, type, parameters
	 * @param String key
	 * @param String type
	 * @param boolean duplicated (if type is duplicated in the data format)
	 * @return String attribute 
	 */
	public static String addAttribute(String key, String type, boolean duplicated, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPERATER + type);
		if(duplicated){ attribute.append(SaladConstants.SEPERATER + type);}
		for(Object o: params){ attribute.append(SaladConstants.SEPERATER + o.toString());}
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, int parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if type is duplicated in the data format)
	 * @param list of object parameters
	 */
	public static String addAttribute(String key, String firstType, int firstParameter, String secondType, boolean duplicated, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPERATER + firstType + SaladConstants.SEPERATER + firstParameter + SaladConstants.SEPERATER + secondType);
		if(duplicated){ attribute.append(SaladConstants.SEPERATER + secondType); }
		for(Object o: params){ attribute.append(SaladConstants.SEPERATER + o.toString()); }
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, String parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if type is duplicated in the data format)
	 * @param list of object parameters
	 */
	public static String addAttribute(String key, String firstType, String firstParameter, String secondType, boolean duplicated, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPERATER + firstType + SaladConstants.SEPERATER + firstParameter + SaladConstants.SEPERATER + secondType);
		if(duplicated){ attribute.append(SaladConstants.SEPERATER + secondType); }
		for(Object o: params){ attribute.append(SaladConstants.SEPERATER + o.toString()); }
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, Double parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if type is duplicated in the data format)
	 * @param list of object parameters
	 */
	public static String addAttribute(String key, String firstType, double firstParameter, String secondType, boolean duplicated, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPERATER + firstType + SaladConstants.SEPERATER + firstParameter + SaladConstants.SEPERATER + secondType);
		if(duplicated){ attribute.append(SaladConstants.SEPERATER + secondType); }
		for(Object o: params){ attribute.append(SaladConstants.SEPERATER + o.toString()); }
		return attribute.toString();
	}
	
	/**
	 * Add attribute of the format as: key, type, int parameter, type, parameters
	 * @param String key
	 * @param String firstType
	 * @param Object firstParameter
	 * @param String secondType
	 * @param boolean duplicated (if type is duplicated in the data format)
	 * @param list of object parameters
	 */
	public static String addAttribute(String key, String firstType, int firstParameter, String secondType, boolean duplicated, Object ... args){
		StringBuilder attribute = new StringBuilder();
		attribute.append(key + SaladConstants.SEPERATER + firstType + SaladConstants.SEPERATER + firstParameter + SaladConstants.SEPERATER + secondType);
		if(duplicated){ attribute.append(SaladConstants.SEPERATER + secondType); }
		for(Object o: args){ attribute.append(SaladConstants.SEPERATER + o.toString()); }
		return attribute.toString();
	}
}
