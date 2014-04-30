package util;

import java.util.ArrayList;

/**
 * @author anthonyotienoolawo
 * 
 * This class parses non-player attributes. 
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.IParser;
import controller.GAEController;

public class NonPlayerAttributeParser {
	private IParser parser;
	 

	public NonPlayerAttributeParser(){ 
		parser = new IParser(); 
	}

	/**
	 * Parses each nonPlayer's attribute string into a Map <String, List<?>> and returns a list of these Maps.
	 * 
	 * @param attributes is a list of Strings. Each String is a comma separated attribute list for an actor.    
	 * @return 
	 */
	public List <Map<String, List<?>>> parseAttributes(List<String> attributes){ 
		List <Map<String, List<?>>> parsedAttributes = new ArrayList <Map<String, List<?>>>(); 
		
		for(String attribute : attributes ){
			parsedAttributes.add(parser.parseToMap(attribute)); 
		}		
		return parsedAttributes; 
	}
	
}
