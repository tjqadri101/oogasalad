package gameFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
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
    protected GameFactory(JGEngine engine){
        myEngine = engine;
        myFormat = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_FORMAT);
        myPath = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_PATH);
    }
    /**
     * Only couple things as argument, use reflection to create or modify object instance.
     */
    public GameObject processOrder(Game game, int levelID, int sceneID, String order) throws FactoryException{
        testLegitimateOrder(order);
        String[] orderSplit = order.split("=");
        String instruction = orderSplit[0];
        List<String> parameterList = parseOrder(instruction, orderSplit[1]);
        Object myObject = Reflection.createInstance(myPath.getString(instruction), 
                                                    parameterList.get(0), 
                                                    parameterList.get(1),
                                                    parameterList.get(2),
                                                    parameterList.get(3),
                                                    parameterList.get(4));  
        return (GameObject) myObject;
    }
    
    /*
     * discuss this again
     */
    private List<String> parseOrder (String instruction, String orderValue) {
        //          checkModifyOrCreate(orderSplit[0]);
        String tokensList = myFormat.getString(instruction);
        List<String> answerList = new ArrayList<String>();
        List<String> inputParameterSplit = Arrays.asList(orderValue.split("\\,"));

        for(int i = 0; i < inputParameterSplit.size(); i ++){
            if(inputParameterSplit.get(i).equals("ParameterToken")){
                answerList.add(inputParameterSplit.get(i));
            }
        }
        return answerList;
    }

    private void testLegitimateOrder (String order) {
        if (!order.contains("=")) 
            throw new IllegalArgumentException("String " + order + " does not contain =");
    }
}
