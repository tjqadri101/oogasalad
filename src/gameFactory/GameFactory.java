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
    
    private GameEngine myEngine;
    private String myOrder;
    private int  myLevelID, mySceneID;
    private Game myGame;
    private Map<String,List<?>> parsedMap;
    private static final String NO_PARAMETER = "";
    private static final String RESOURCE_PACKAGE = "engineResources/";
    private static final String DEFAULT_FORMAT= "DataFormat";
    private static final String DEFAULT_PATH= "FactoryOrderPath"; 
    private static final String DEFAULT_METHOD= "TypeMethodMatcher";
    protected ResourceBundle myFormat, myPath, myMethod;
        private String instruction;
        private List<Object> objArgList;
        private List<String> typeMethodList;
    
    
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
        
        instruction = (String) order.get(0);
        parsedMap = parseOrder(order, instruction);
        List<Object> objArgList = (List<Object>) parsedMap.get("Argument");
//        System.out.println("the typeMethodList in the gameFactory after parsed is " + typeMethodList);
               
        String reflectionChoice = Arrays.asList(myPath.getString(instruction).split("\\,")).get(0);
        String RFIndicator = Arrays.asList(myPath.getString(instruction).split("\\,")).get(1);   
        String GameRefMethod = Arrays.asList(myPath.getString(instruction).split("\\,")).get(2);
        String GameRefPara= Arrays.asList(myPath.getString(instruction).split("\\,")).get(3);
        
        Object refObj = Reflection.callMethod(this, "get"+RFIndicator); 
        return (GameObject) Reflection.callMethod(this, reflectionChoice+"Reflect", myLevelID, mySceneID, objArgList, 
                                                  refObj, GameRefMethod, GameRefPara);
    }
    
    /**
     * Creation or modification via Engine (See FactoryOrderPath.Properties or exhaustive list of create/modify through Engine)
     */
    @SuppressWarnings("unused")
    public GameObject oneStepReflect (int levelID, int sceneID, List<Object> objArgList, 
                                      Object refObj, String GameReflectInfo, String GameRefPara) 
                                        throws FactoryException {
        
        String methodToInvoke = myMethod.getString(instruction);
        
        Object[] objArgArray = objArgList.toArray(new Object[objArgList.size()]);

        return (GameObject) Reflection.callMethod(refObj, methodToInvoke, objArgArray);
    }
    
    /**
     * Creation or modification via Game (See FactoryOrderPath.Properties for exhaustive list of create/modify through Game)
     */
    // TODO: to extract if selection and IDSelector into another layer of reflection
    @SuppressWarnings("unused")
    public GameObject twoStepReflect (int levelID, int sceneID, List<Object> objArgList, 
                                      Object refObj, String GameRefMethod, String GameReflectPara) 
                                      throws FactoryException {
        Object obj = null;
        
        List<String> typeMethodList =  (List<String>) parsedMap.get("Type");
        String methodToInvoke = myMethod.getString(typeMethodList.get(1));

        int objectID = (Integer) objArgList.remove(0);

        Object[][] IDSelector = new Object[][]{new Object[]{levelID},
                                new Object[]{levelID, sceneID},
                                new Object[]{levelID, sceneID, objectID}};
        //new Object[]{objectID},
        if (!GameReflectPara.equals(NO_PARAMETER)){
            int numArg = Integer.parseInt(GameReflectPara);
            obj = Reflection.callMethod(refObj, GameRefMethod, IDSelector[numArg]);
        }
        else{
            obj = Reflection.callMethod(refObj, GameRefMethod);
            // the case for player, gravity... those who does not take-in any parameter
        }
        Object[] argumentArray = objArgList.toArray(new Object[objArgList.size()]);
        return (GameObject) Reflection.callMethod(obj, methodToInvoke, argumentArray);
    }
    
    /**
     * Only couple things as argument, use reflection to create or modify object instance.
     * @param order
     * @param instruction
     * @return Map<String, List<Obj>>
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

    /**
     * Getting Game instance to reflect upon. To be called using the reflection to get refObj*/
    public Game getGame () {
        return myGame;
    }
    
    /**
     * Getting Engine instance to reflect upon. To be called using the reflection to get refObj*/
    public GameEngine getEngine () {
        return (GameEngine) myEngine;
//        return (Game) Reflection.callMethod(myEngine, methodToInvoke, objArgArray);
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
