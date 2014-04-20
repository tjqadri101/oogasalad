package gameFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import engine.GameEngine;
import objects.GameObject;
import reflection.Reflection;
import gameFactory.FactoryException;
import stage.Game;
import util.IParser;

/**
 * @Author: Steve (Siyang) Wang, 
 * @VirtualCo-Author: Issac (Shenghan) Chen
 */
public class GameFactory {
    
    private GameEngine myEngine;
    private int  myLevelID, mySceneID;
    private Game myGame;
    private IParser p;
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
        p = new IParser();
    }
    
    /**
     * only public method, takes in creation or modification order
     */
    @SuppressWarnings("unchecked")
    public GameObject processOrder(String order) {
        
        myLevelID = ((GameEngine) myEngine).getCurrentLevelID();
        mySceneID = ((GameEngine) myEngine).getCurrentSceneID();
        
        objArgList = (List<Object>) p.parseParameter(order);
        typeMethodList =  (List<String>) p.parseType(order);
        instruction = p.getOrderKey(order);
        
//        System.out.println("the typeMethodList in the gameFactory after parsed is " + typeMethodList);
               
        String reflectionChoice = Arrays.asList(myPath.getString(instruction).split("\\,")).get(0);
        String RFIndicator = Arrays.asList(myPath.getString(instruction).split("\\,")).get(1);   
        String GameRefMethod = Arrays.asList(myPath.getString(instruction).split("\\,")).get(2);
        String GameRefPara= Arrays.asList(myPath.getString(instruction).split("\\,")).get(3);
        
        Object refObj = Reflection.callMethod(this, "get"+RFIndicator); 
        return (GameObject) Reflection.callMethod(this, reflectionChoice+"Reflect", myLevelID, mySceneID, 
                                                  refObj, GameRefMethod, GameRefPara);
    }
    
    /**
     * Creation or modification via Engine (See FactoryOrderPath.Properties or exhaustive list of create/modify through Engine)
     */
    public GameObject oneStepReflect (int levelID, int sceneID, 
                                      Object refObj, String GameReflectInfo, String GameRefPara) 
                                        throws FactoryException {
        
        String methodToInvoke = myMethod.getString(instruction);
        
        Object[] objArgArray = objArgList.toArray(new Object[objArgList.size()]);

        return (GameObject) Reflection.callMethod(refObj, methodToInvoke, objArgArray);
    }
    
    /**
     * Creation or modification via Game (See FactoryOrderPath.Properties for exhaustive list of create/modify through Game)
     */
    @SuppressWarnings("unchecked")
    public GameObject twoStepReflect (int levelID, int sceneID, 
                                      Object refObj, String GameRefMethod, String GameReflectPara) 
                                      throws FactoryException {
        Object obj = null;
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
            // when getting player, gravity... those who does not take-in any parameter
        }
        Object[] argumentArray = objArgList.toArray(new Object[objArgList.size()]);
        return (GameObject) Reflection.callMethod(obj, methodToInvoke, argumentArray);
    }

    /**
     * Getting Game instance to reflect upon. To be called using the reflection to get refObj
     * Do not call this directly*/
    public Game getGame () {
        return myGame;
    }
    
    /**
     * Getting Engine instance to reflect upon. To be called using the reflection to get refObj
     * Do not call this directly*/
    public GameEngine getEngine () {
        return (GameEngine) myEngine;

    }
    
    /**
     * Test the legitimacy of an order passed from GAE
     */
    @SuppressWarnings("unused")
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
