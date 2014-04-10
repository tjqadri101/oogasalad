package gameFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
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
    public static final String RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_FORMAT= "DataFormat";
    public static final String DEFAULT_PATH= "FactoryOrderPath"; 
    protected ResourceBundle myFormat, myPath, myReflection;
    
    public GameFactory(GameEngine engine){
        myEngine = engine;
        myGame = ((GameEngine) myEngine).getGame();
        myFormat = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_FORMAT);
        myPath = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_PATH);
    }
    
    /**
     * only public method, takes in creation or modification order
     */
    public GameObject processOrder(List<Object> order) {
        
        myLevelID = ((GameEngine) myEngine).getCurrentLevelID();
        mySceneID = ((GameEngine) myEngine).getCurrentSceneID();
        
        String instruction = (String) order.get(0);
        List<Object> objArgList = parseOrder(order, instruction);
        
        String reflectionChoice = Arrays.asList(myPath.getString(instruction).split("\\,")).get(0);
        String methodToInvoke = Arrays.asList(myPath.getString(instruction).split("\\,")).get(1);
        String GameRefMethod = Arrays.asList(myPath.getString(instruction).split("\\,")).get(2);
        String GameRefPara= Arrays.asList(myPath.getString(instruction).split("\\,")).get(3);
        
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
        Object[] argumentArray = objArgList.toArray(new Object[objArgList.size()]);

        int[][] IDSelector = new int[][]{new int[]{(int) objArgList.get(0)},
                                         new int[]{(int) objArgList.get(0),levelID},
                                         new int[]{(int) objArgList.get(0),levelID, sceneID}};
        //        Class<?> c = Class.forName(GameReflectionInfo);
        
        Object obj = Reflection.callMethod(game, GameRefMethod, IDSelector[numArg]);
        return (GameObject) Reflection.callMethod(obj, methodToInvoke, argumentArray);
    }
    
    /**
     * Only couple things as argument, use reflection to create or modify object instance.
     * @param order
     * @param instruction
     * @return
     */
    public List<Object> parseOrder (List<Object> order, String instruction) {
        String tokens = myFormat.getString(instruction);
        List<String> tokenList = Arrays.asList(tokens.split("\\,"));
        List<Object> argumentList = new ArrayList<Object>();
        for(int i = 1; i < order.size(); i ++){
            if(tokenList.get(i-1).equals("ParameterToken")){
                argumentList.add(order.get(i));
            }
        }
        return argumentList;
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
