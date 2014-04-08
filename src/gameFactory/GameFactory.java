package gameFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import objects.GameObject;
import reflection.Reflection;

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
    public static final String DEFAULT_NULL = "null";

    private static final String TEST_STRING = ""; 
    protected ResourceBundle myFormat, myPath;
    public GameFactory(JGEngine engine){
            myEngine = engine;
            myFormat = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_FORMAT);
            myPath = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_PATH);
    }
    /**
      * Only couple things as argument, use reflection to create or modify object instance.
      */
    public GameObject processOrder(Game game, int levelID, int sceneID, String order) throws FactoryException{
//            testLegitimateOrder(order);
//            String[] orderSplit = order.split("=");
//            String instruction = orderSplit[0];
            List<String> parameterList = parseOrder(order);
            String className = myPath.getString(parameterList.get(0));
            Object myObject = Reflection.createInstance(className, 
                                                        parameterList.get(1), 
                                                        parameterList.get(2),
                                                        parameterList.get(3),
                                                        parameterList.get(4),
                                                        parameterList.get(5));  
            return (GameObject) myObject;
     }

        /*
         * discuss this again
         */
        private List<String> parseOrder (String order) {
            //          checkModifyOrCreate(orderSplit[0]);
            
            List<String> inputSplit = Arrays.asList(order.split("\\,"));
            String tokensList = myFormat.getString(inputSplit.get(0));
            List<String> instructionList = Arrays.asList(tokensList.split("\\,"));
            List<String> answerList = new ArrayList<String>();
            for(int i = 0; i < inputSplit.size() - 1; i ++){
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
