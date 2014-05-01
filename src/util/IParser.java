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
 * @author Justin (Zihao) Zhang 
 * @Co-author Steve (Siyang) Wang
 */

public class IParser {
    
    private static Map<String,List<?>> parsedResults = new HashMap<String, List<?>>();
    public static ResourceBundle myDataFormat;
    public static ResourceBundle myReflectionMethods;
    
    /**
     * Test the legitimacy of an order passed
     */
    private void testLegitimateOrder (String order) {
        if (!order.contains(SaladConstants.SEPARATOR)) 
            throw new IllegalArgumentException("String " + order + " does not contain " + SaladConstants.SEPARATOR);
    }

    /**
     * Called to parse a String order into a Map of String to Parsed Result (i.e. "ParameterList" to List<Object>") 
     * Key String "All" to List<Object> order 
     * Key String "Type" to List<String> TypeTokens
     * Key String "Parameter" to List<Object> Parameters
     */
    public Map<String, List<?>> parseToMap (String order){
        myDataFormat = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.TYPE_FORMAT);
        myReflectionMethods = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.DATA_FORMAT_REFLECTION);
        
            List<Object> allAnswer = new ArrayList<Object>();
            List<String> typeAnswer = new ArrayList<String>();
            List<Object> parameterAnswer = new ArrayList<Object>();
            
            testLegitimateOrder(order);
            String[] orders = order.split(SaladConstants.SEPARATOR);
            int i = 0;
            allAnswer.add(orders[i]); //add key
            i ++;
            while(i < orders.length){
//                    System.out.println("IParser:" + orders[i]);
                    allAnswer.add(orders[i]); //add type
                    typeAnswer.add(orders[i]); //add type to the typeAnswer
                    String type = myDataFormat.getString(orders[i]);
                    
                    String[] types = type.split(SaladConstants.SEPARATOR); 
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
    
    public String getOrderKey(String order){
            return order.split(SaladConstants.SEPARATOR)[0];
    }
    
    /**
     * Do not call this method directly; called by Reflection within IParser
     */
    public Boolean convertStringToBoolean(String s){
        if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
            return Boolean.valueOf(s);
        } else {
            throw new ClassCastException(); // should not reach here
        }
    }
    
    /**
     * Do not call this method directly; called by Reflection within IParser
     */
    public Character convertStringToChar(String s){
        return s.charAt(0);
    }
    
    /**
     * Do not call this method directly; called by Reflection within IParser
     */
    public Integer convertStringToInteger(String s){
        return Integer.valueOf(s);
    }

    /**
     * Do not call this method directly; called by Reflection within IParser
     */
    public Double convertStringToDouble(String s){
        return Double.valueOf(s);
    }

    /**
     * Do not call this method directly; called by Reflection within IParser
     */
    public String convertStringToString(String s){
        return s;
    }


    
}
