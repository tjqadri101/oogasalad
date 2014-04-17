package gameFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import engine.GameEngine;
import objects.GameObject;
import reflection.Reflection;
import reflection.ReflectionException;

import gameFactory.FactoryException;
import objects.GameObject;
import reflection.Reflection;
import stage.Game;
import jgame.JGObject;
import jgame.platform.JGEngine;
import reflection.*;

/**
 * @Author: Steve (Siyang) Wang, 
 * @VirtualCo-Author: Issac (Shenghan) Chen
 */
public class GameFactory {
    private JGEngine myEngine;
    private String myOrder;
    private int  myLevelID, mySceneID;
    private Game myGame;
    private Map<String,List<?>> parsedMap;
    public static final String RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_FORMAT= "DataFormat";
    public static final String DEFAULT_PATH= "FactoryOrderPath"; 
    public static final String DEFAULT_METHOD= "TypeMethodMatcher";
    protected ResourceBundle myFormat, myPath, myMethod;
    
    public GameFactory(GameEngine engine){
        myEngine = engine;
        myGame = ((GameEngine) myEngine).getGame();
        myFormat = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_FORMAT);
        myPath = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_PATH);
        myMethod = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_METHOD);
    }
    
    /**
     * only public method, takes in creation or modification order
     */
    public GameObject processOrder(List<Object> order) {
        
        myLevelID = ((GameEngine) myEngine).getCurrentLevelID();
        mySceneID = ((GameEngine) myEngine).getCurrentSceneID();
        
        String instruction = (String) order.get(0);
        parsedMap = parseOrder(order, instruction);
        List<Object> objArgList = (List<Object>) parsedMap.get("Argument");
        List<String> typeMethodList =  (List<String>) parsedMap.get("Type");
//        System.out.println("the typeMethodList in the gameFactory after parsed is " + typeMethodList);
        
        String reflectionChoice = Arrays.asList(myPath.getString(instruction).split("\\,")).get(0);
        String methodToInvoke = myMethod.getString(typeMethodList.get(1));
        String GameRefMethod = Arrays.asList(myPath.getString(instruction).split("\\,")).get(1);
        String GameRefPara= Arrays.asList(myPath.getString(instruction).split("\\,")).get(2);
        
        return (GameObject) Reflection.callMethod(this, reflectionChoice+"Reflect", myLevelID, mySceneID, objArgList, 
                              methodToInvoke, myGame, GameRefMethod, GameRefPara);
    }
    
    /**
     * Creation or modification via Engine (See FactoryOrderPath.Properties or exhaustive list of create/modify through Engine)
     */
    @SuppressWarnings("unused")
    public GameObject EngineReflect (int levelID, int sceneID, List<Object> objArgList, 
                                String methodToInvoke, Game game, String GameReflectInfo, String GameRefPara) 
                                        throws FactoryException {

        Object[] objArgArray = objArgList.toArray(new Object[objArgList.size()]);

        return (GameObject) Reflection.callMethod(myEngine, methodToInvoke, objArgArray);
    }
    
    /**
     * Creation or modification via Game (See FactoryOrderPath.Properties or exhaustive list of create/modify through Game)
     */
    @SuppressWarnings("unused")
    public GameObject GameReflect (int levelID, int sceneID, List<Object> objArgList, 
                              String methodToInvoke, Game game, String GameRefMethod, String GameReflectPara) 
                                      throws FactoryException {

        int numArg = Integer.parseInt(GameReflectPara);
        int objectID = (int) objArgList.remove(0);

        Object[][] IDSelector = new Object[][]{new Object[]{objectID},
                                new Object[]{levelID, objectID},
                                new Object[]{levelID, sceneID, objectID}};

        Object[] argumentArray = objArgList.toArray(new Object[objArgList.size()]);
        
        Object obj = Reflection.callMethod(game, GameRefMethod, IDSelector[numArg]);
        return (GameObject) Reflection.callMethod(obj, methodToInvoke, argumentArray);
    }
    
    /**
     * Only couple things as argument, use reflection to create or modify object instance.
     * @param order
     * @param instruction
     * @return
     */
    public Map<String, List<?>> parseOrder (List<Object> order, String instruction) {
        String formatToken = myFormat.getString(instruction);
        List<String> formatTokenList = Arrays.asList(formatToken.split("\\,"));
        List<Object> argumentList = new ArrayList<Object>();
        List<String> typeList = new ArrayList<String>();
        for(int i = 1; i < order.size(); i ++){
            if(formatTokenList.get(i-1).equals("ParameterToken")){
                argumentList.add(order.get(i));
            }
            if(formatTokenList.get(i-1).equals("TypeToken")){
                typeList.add((String) order.get(i));
            }
        }
        Map<String, List<?>> returnMap = new HashMap<String, List<?>>();
        returnMap.put("Argument", argumentList);
        returnMap.put("Type", typeList);
        return returnMap;
    }
    
    // Need to implement if the order format is not changed. Discuss tmr
    private int intParse(String s){
        return 0;
        
    }
    
    private double doubleParse(String s){
        return 0.0;
    }
    
    /**
     * Test the legitimacy of an order passed from GAE
     */
    private void testLegitimateOrder (String order) {
        if (!order.contains(",")) 
            throw new IllegalArgumentException("String " + order + " does not contain =");
    }
}

/*      // Optional parsing by splitting strings, converting them into respective types, and concatonate to Obj Array 
 *      // as indicated in the following link 
 *  
 *      // http://stackoverflow.com/questions/11022208/how-do-i-use-reflection-to-invoke-a-method-with-parameters
 *      // http://yourmitra.wordpress.com/2008/09/26/using-java-reflection-to-invoke-a-method-with-array-parameters/
 *      */
