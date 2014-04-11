package gameFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
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
    public static final String DEFAULT_REFLECTION= "ArgumentNumberReflection";
    public static final String DEFAULT_NULL = "null";

    private static final String TEST_STRING = ""; 
    protected ResourceBundle myFormat, myPath, myReflection;
    
    public GameFactory(JGEngine engine){
        myEngine = engine;
        myFormat = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_FORMAT);
        myPath = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_PATH);
    }
    
    /**
     * only public method, takes in creation or modification order
     */
    public void processOrder(Game game, int levelID, int sceneID, String order) {
       
/*      // Optional parsing by splitting strings, converting them into respective types, and concatonate to Obj Array 
 *      // as indicated in the following link 
 *  
 *      // http://stackoverflow.com/questions/11022208/how-do-i-use-reflection-to-invoke-a-method-with-parameters
 *      // http://yourmitra.wordpress.com/2008/09/26/using-java-reflection-to-invoke-a-method-with-array-parameters/
 * 
 *      */
        
        List<String> orderSplit = Arrays.asList(order.split("\\,"));
        String instruction = orderSplit.get(0);
        String tokens = myFormat.getString(instruction);
        List<String> tokenList = Arrays.asList(tokens.split("\\,"));
        List<String> argumentList = new ArrayList<String>();
        for(int i = 0; i < orderSplit.size(); i ++){
            if(tokenList.get(i).equals("ParameterToken")){
                argumentList.add((String) orderSplit.get(i));
            }
        }
        
        String reflectionChoice = Arrays.asList(myPath.getString(instruction).split("\\,")).get(0);
        String methodToInvoke = Arrays.asList(myPath.getString(instruction).split("\\,")).get(1);
        String GameRefMethod = Arrays.asList(myPath.getString(instruction).split("\\,")).get(2);
        String GameRefPara= Arrays.asList(myPath.getString(instruction).split("\\,")).get(3);
        Reflection.callMethod(this, reflectionChoice+"Reflect", levelID, sceneID, argumentList, 
                              methodToInvoke, game, GameRefMethod, GameRefPara);
    }
    
    /**
     * Creation or modification via Engine (See FactoryOrderPath.Properties or exhaustive list of create/modify through Engine)
     */
    @SuppressWarnings("unused")
    private void EngineReflect (int levelID, int sceneID, List<String> argumentList, 
                                String methodToInvoke, Game game, String GameReflectInfo, String GameRefPara) 
                                        throws FactoryException {

        String [] argumentArray = argumentList.toArray(new String[argumentList.size()]);

        Reflection.callMethod(myEngine, methodToInvoke, argumentArray);
    }
    
    /**
     * Creation or modification via Game (See FactoryOrderPath.Properties or exhaustive list of create/modify through Game)
     */
    @SuppressWarnings("unused")
    private void GameReflect (int levelID, int sceneID, List<String> argumentList, 
                              String methodToInvoke, Game game, String GameRefMethod, String GameRefPara) 
                                      throws FactoryException {

        int numArg = Integer.parseInt(GameRefPara);
        String [] argumentArray = argumentList.toArray(new String[argumentList.size()]);

        int[][] IDSelector = new int[][]{new int[]{Integer.parseInt(argumentList.get(0))},
                                         new int[]{Integer.parseInt(argumentList.get(0)),levelID},
                                         new int[]{Integer.parseInt(argumentList.get(0)),levelID, sceneID}};
        //        Class<?> c = Class.forName(GameReflectionInfo);
        Object obj = Reflection.callMethod(game, GameRefMethod, IDSelector[numArg]);
        Reflection.callMethod(obj, methodToInvoke, argumentArray);
    }
    
    /**
     * Only couple things as argument, use reflection to create or modify object instance.
     */
    private List<String> parseOrder (String order) {
        //          checkModifyOrCreate(orderSplit[0]);
        List<String> inputSplit = Arrays.asList(order.split("\\,"));
        String tokensList = myFormat.getString(inputSplit.get(0));
        List<String> instructionList = Arrays.asList(tokensList.split("\\,"));
        List<String> answerList = new ArrayList<String>();
        for(int i = 0; i < instructionList.size() - 1; i ++){
            if(instructionList.get(i).equals("ParameterToken")){
                answerList.add(inputSplit.get(i));
            }
        }
        return answerList;
    }
    
    // Need to implement if the order format is not changed. Discuss tmr
    private int intParse(String s){
        return 0;
        
    }
    
    /**
     * Test the legitimacy of an order passed via GAE
     */
    private void testLegitimateOrder (String order) {
        if (!order.contains(",")) 
            throw new IllegalArgumentException("String " + order + " does not contain =");
    }
}
