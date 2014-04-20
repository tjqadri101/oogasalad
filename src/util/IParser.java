package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import reflection.Reflection;
import saladConstants.SaladConstants;
import stage.Game;
import engine.GameEngine;

/** Intended for universal parsing in the program
 * @author Steve (Siyang) Wang
 * @Co-author: Justin (Zihao) Zhang
 */

public class IParser {
    
    private static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    private static final String DEFAULT_DATA_FORMAT = "TypeFormat";
    private static final String DEFAULT_REFLECTION_METHODS = "DataFormatReflection";
    private static Map<String,List<?>> parsedResults = new HashMap<String, List<?>>();
    public static ResourceBundle myDataFormat;
    public static ResourceBundle myReflectionMethods;
    private static Object self;
    private static Object SaladUtil;

    /**
     * Called to parse a String order into a Map of String to Parsed Result (i.e. "ParameterList" to List<Obejct>") 
     * Key String "All" to List<Object> order 
     * Key String "Type" to List<String> TypeTokens
     * Key String "Parameter" to List<Object> Parameters
     */
    public Map<String, List<?>> parseToMap (String order){
        myDataFormat = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_DATA_FORMAT);
        myReflectionMethods = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_REFLECTION_METHODS);
        
            List<Object> allAnswer = new ArrayList<Object>();
            List<String> typeAnswer = new ArrayList<String>();
            List<Object> parameterAnswer = new ArrayList<Object>();
            
            String[] orders = order.split(",");
            int i = 0;
            allAnswer.add(orders[i]); //add key
            i ++;
            while(i < orders.length){
                    allAnswer.add(orders[i]); //add type
                    typeAnswer.add(orders[i]); //add type to the typeAnswer
                    String type = myDataFormat.getString(orders[i]);
                    String[] types = type.split(","); 
                    if(!types[0].equals(SaladConstants.NULL_TOKEN)){
                            i = i + 1;
                            for(int j = 0; j < types.length; j ++){
                                    Object item = Reflection.callMethod(this, myReflectionMethods.getString(types[j]), orders[i+j]);
                                    allAnswer.add(item); //add the item to the AllAnswer
                                    parameterAnswer.add(item); //add the item to the parameterAnswer
                            }
                    }
                    i = i + types.length;
            }
            parsedResults.put("All", allAnswer);
            parsedResults.put("Type", typeAnswer);
            parsedResults.put("Parameter", parameterAnswer);
            return parsedResults;
    }
    
    @SuppressWarnings("unchecked")
    public List<Object> parseAll(String order){
            return (List<Object>) parseToMap(order).get("All");
    }
    
    @SuppressWarnings("unchecked")
    public List<String> parseType(String order){
            return (List<String>) parseToMap(order).get("Type");
    }
    
    @SuppressWarnings("unchecked")
    public List<Object> parseParameter(String order){
            return (List<Object>) parseToMap(order).get("Parameter");
    }
    
    /**
     * Do not call this method directly; called by Reflection within DataController
     */
    public Integer convertStringToInteger(String s){
        return Integer.valueOf(s);
    }

    /**
     * Do not call this method directly; called by Reflection within DataController
     */
    public Double convertStringToDouble(String s){
        return Double.valueOf(s);
    }

    /**
     * Do not call this method directly; called by Reflection within DataController
     */
    public String convertStringToString(String s){
        return s;
    }

    
}
