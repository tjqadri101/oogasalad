package gameFactory;

import java.util.Arrays;
import java.util.List;
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
    private static final String NO_PARAMETER = "\"\"";
    private static final String REGEX = "\\,";
    private static final String RESOURCE_PACKAGE = "engineResources/";
    private static final String DEFAULT_FORMAT= "DataFormat";
    private static final String DEFAULT_PATH= "OrderPath"; 
    private static final String DEFAULT_METHOD= "MethodMatcher";
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
    public GameObject processOrder(String order) {

        myLevelID = ((GameEngine) myEngine).getCurrentLevelID();
        mySceneID = ((GameEngine) myEngine).getCurrentSceneID();

        objArgList = (List<Object>) p.parseParameter(order);
        typeMethodList =  (List<String>) p.parseType(order);
        instruction = p.getOrderKey(order);

        //      TODO: Simplify code below:       
        String reflectionChoice = Arrays.asList(myPath.getString(instruction).split(REGEX)).get(0);
        String RFIndicator = Arrays.asList(myPath.getString(instruction).split(REGEX)).get(1);   
        String GameRefMethod = Arrays.asList(myPath.getString(instruction).split(REGEX)).get(2);
        String GameRefPara= Arrays.asList(myPath.getString(instruction).split(REGEX)).get(3);

        Object refObj = Reflection.callMethod(this, "get"+RFIndicator); 
        return (GameObject) Reflection.callMethod(this, reflectionChoice+"Reflect", 
                                                  refObj, GameRefMethod, GameRefPara);
    }

    /**
     * Creation or modification through oneStep reflection:
     *  invoke method directly
     *  (See FactoryOrderPath.Properties or exhaustive list of create/modify through Engine)
     */
    public GameObject oneStepReflect (Object refObj, String GameRefInfo, String GameReflectPara) 
            throws FactoryException {

        String methodToInvoke = myMethod.getString(instruction);
        Object[] objArgArray;
        
        if (!(GameReflectPara.equals(NO_PARAMETER))) {
            int numArg = Integer.parseInt(GameReflectPara);
            objArgArray = idSelector(numArg); 
        }
        else {
            objArgArray = objArgList.toArray(new Object[objArgList.size()]);
        }
        return (GameObject) Reflection.callMethod(refObj, methodToInvoke, objArgArray);
    }

    /**
     * Creation or modification through twoStep Reflection 
     *          First need to grab the instance of object
     *          Then need invoke method on the instance
     *  (See FactoryOrderPath.Properties for exhaustive list of create/modify through Game)
     */
    public GameObject twoStepReflect (Object refObj, String GameRefMethod, String GameReflectPara) 
            throws FactoryException {
        Object obj = null;
        String methodToInvoke = myMethod.getString(typeMethodList.get(1));

        if (!GameReflectPara.equals(NO_PARAMETER)){
            int numArg = Integer.parseInt(GameReflectPara);
            obj = Reflection.callMethod(refObj, GameRefMethod, idSelector(numArg));
        }
        else{
            obj = Reflection.callMethod(refObj, GameRefMethod);
            // when getting player, gravity... those who does not take-in any parameter
        }
        Object[] argumentArray = objArgList.toArray(new Object[objArgList.size()]);
        return (GameObject) Reflection.callMethod(obj, methodToInvoke, argumentArray);
    }
    
    /**Takes in CurrentLevelID and CurrentSceneID to produce 
     * @param levelID
     * @param sceneID
     * @return the desired array parameter
     */
    private Object[] idSelector (int numArg) {
        int objectID = (Integer) objArgList.remove(0);
        Object[][] IDAugmentor = new Object[][]{new Object[]{myLevelID},
                                                new Object[]{myLevelID, mySceneID},
                                                new Object[]{myLevelID, mySceneID, objectID}};
        return (Object[]) IDAugmentor[numArg];
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

}

/*      // Optional parsing by splitting strings, converting them into respective types, and concatonate to Obj Array 
 *      // as indicated in the following link 
 *  
 *      // http://stackoverflow.com/questions/11022208/how-do-i-use-reflection-to-invoke-a-method-with-parameters
 *      // http://yourmitra.wordpress.com/2008/09/26/using-java-reflection-to-invoke-a-method-with-array-parameters/
 *      */
