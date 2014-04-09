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
 * @Author: Steve (Siyang) Wang
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
        myReflection = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_REFLECTION);
    }
    /**
     * Only couple things as argument, use reflection to create or modify object instance.
     */
    public GameObject processOrder(Game game, int levelID, int sceneID, String order) throws FactoryException{

        List<String> orderSplit = Arrays.asList(order.split("\\,"));
        String instruction = orderSplit.get(0);
        String tokens = myFormat.getString(instruction);
        List<String> tokenList = Arrays.asList(tokens.split("\\,"));
        List<String> argumentList = new ArrayList<String>();
        for(int i = 0; i < orderSplit.size() - 1; i ++){
            if(tokenList.get(i).equals("ParameterToken")){
                argumentList.add((String) orderSplit.get(i));
            }
        }
        int[][] IDArrays = new int[][]{new int[]{Integer.parseInt(argumentList.get(0))},new int[]{argumentList.get(0),levelID},new int[]{argumentList.get(0),levelID, sceneID}};
        
        argumentList.add();
        String [] argumentArray = argumentList.toArray(new String[argumentList.size()]);
        
        Reflection.callMethod(myEngine, myPath.getString(instruction), argumentArray);
        
        generalReflection(levelID, sceneID, argumentList);
        
        Reflection.callMethod(, myReflection.getString(argumentList.size()+""), 
                              argumentList.get(1), 
                              argumentList.get(2),
                              argumentList.get(3),
                              argumentList.get(4),
                              argumentList.get(5),
                              levelID,
                              sceneID);

        String reflectionType = myPath.getString(argumentList.get(0));
        
        String className = myPath.getString(instruction);
        GameObject myObject = (GameObject) Reflection.createInstance(className, 
                                                                     tokenList.toArray());
        answerList.get(1), 
        answerList.get(2),
        answerList.get(3),
        answerList.get(4),
        answerList.get(5),
        levelID,s
        sceneID);
        return myObject;
    }


    private void generalReflection (int levelID, int sceneID, List<String> argumentList ) throws FactoryException, ReflectionException{ 
        if(argumentList.equals(DEFAULT_NULL)) return; {
            try
            {   
                sixArgumentReflection(levelID, sceneID, argumentList);
            }
            catch (Exception e){
                throw new ReflectionException("No matching public method " + myReflection.getString(argumentList.size()+"") + 
                                              " for " + this.getClass().getName());}
        }
        return;
    }

    class Y implements callableInterface
    {
        public void call (List<String> argumentList) {
            Reflection.callMethod(this, myReflection.getString(argumentList.size()+""), 
                                  argumentList.get(1), 
                                  argumentList.get(2),
                                  argumentList.get(3),
                                  argumentList.get(4),
                                  argumentList.get(5),
                                  levelID,
                                  sceneID);
            //                Method toCall = this.getClass().getDeclaredMethod(myReflection.getString(argumentList.size()+""), new Class[0]);
        }

        class X implements callableInterface
        {
            public X (weird parameters)
            {
                save weird parameters
            }

            public void call (List<String> argumentList) {
                Reflection.callMethod(this, myReflection.getString(argumentList.size()+""), 
                                      argumentList.get(1), 
                                      argumentList.get(2),
                                      argumentList.get(3),
                                      saved weird arguments);
                //                Method toCall = this.getClass().getDeclaredMethod(myReflection.getString(argumentList.size()+""), new Class[0]);
            }

            public void sixArgumentReflection(Game game, int levelID, int sceneID, List<String> argumentList) throws FactoryException{
                List<String> parameterList = parseOrder(order);
                String className = myPath.getString(parameterList.get(0));
                Reflection.callMethod(myEngine, myPath.getString(argumentList.size()+""), 
                                      argumentList.get(1), 
                                      argumentList.get(2),
                                      argumentList.get(3),
                                      argumentList.get(4),
                                      argumentList.get(5),
                                      levelID,
                                      sceneID);
            }





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

            private void testLegitimateOrder (String order) {
                if (!order.contains("=")) 
                    throw new IllegalArgumentException("String " + order + " does not contain =");
            }
        }
    }
}

/*  // for reference only
    methods = new HashMap<String, CallableInterface>();
    methods[6] = new X(weird parameters);
    // solution is to just pass an Array inside. Figure out how to make array of similar things 
    // outside of reflection part
    methods["X"].call(argumentList);*/
