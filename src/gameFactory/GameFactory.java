package gameFactory;

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
 * @Help: Issac (Shenghan) Chen
 */
public class GameFactory {

    private static final String LEVEL_SCENE_OBJECT = "LevelSceneObject";
    private static final String LEVEL_SCENE = "LevelScene";
    private static final String OBJECT_ID = "objectID";
    private static final String LEVEL = "level";
    private static final String GAME_STATE = "GameState";
    private static final String REFLECT = "Reflect";
    private static final String PREFIX_GET = "get";
    private static final String NO_PARAMETER = "\"\"";
    private static final String REGEX = "\\,";
    private static final String RESOURCE_PACKAGE = "engineResources/";
    private static final String DEFAULT_FORMAT= "DataFormat";
    private static final String DEFAULT_PATH= "OrderPath"; 
    private static final String DEFAULT_METHOD= "MethodMatcher";
    private GameEngine myEngine;
    private int  myLevelID, mySceneID;
    private Game myGame;
    private IParser p;
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
        System.out.println("processOrder: the objArgList is " + objArgList);
        typeMethodList =  (List<String>) p.parseType(order);
        System.out.println("ProcessOrder: typeMethodList is :" + typeMethodList);
        instruction = p.getOrderKey(order);

        //      TODO: Simplify code below:       
        String reflectionChoice = Arrays.asList(myPath.getString(instruction).split(REGEX)).get(0);
        String RFIndicator = Arrays.asList(myPath.getString(instruction).split(REGEX)).get(1);   
        String GameRefMethod = Arrays.asList(myPath.getString(instruction).split(REGEX)).get(2);
        String GameRefPara= Arrays.asList(myPath.getString(instruction).split(REGEX)).get(3);

        Object refObj = Reflection.callMethod(this, PREFIX_GET+RFIndicator); 
        return (GameObject) Reflection.callMethod(this, reflectionChoice+REFLECT, 
                                                  refObj, GameRefMethod, GameRefPara);
    }

    /**
     * Creation or modification through oneStep reflection:
     *  invoke method directly
     *  (See FactoryOrderPath.Properties or exhaustive list of create/modify through Engine)
     *  methodMatcher directly matches the Key here
     */
    
    public GameObject oneStepReflect (Object refObj, String GameRefInfo, String GameReflectPara) 
            throws FactoryException {

        String methodToInvoke = myMethod.getString(instruction);
        Object[] objArgArray;
        
        if (!(GameReflectPara.equals(NO_PARAMETER))) {
            objArgArray = idSelector(GameReflectPara); 
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
     *  methodMatcher directly matches via the second element of typeMethodList
     */
    public GameObject twoStepReflect (Object refObj, String GameRefMethod, String GameReflectPara)
            throws FactoryException {
        Object obj = null;
        String methodToInvoke = null;
        if (!GameReflectPara.equals(NO_PARAMETER)){
//            System.out.println("twoStepReflect: with para-ref");
            methodToInvoke = myMethod.getString(typeMethodList.get(1));
            obj = Reflection.callMethod(refObj, GameRefMethod, idSelector(GameReflectPara));
        }
        else{
//            System.out.println("twoStepReflect: no para-ref");
            String tempMethod = typeMethodList.get(0);
            if(!tempMethod.equals("Colid")&&!tempMethod.equals("ID")) { methodToInvoke = myMethod.getString(tempMethod);}
            else{methodToInvoke = myMethod.getString(typeMethodList.get(1));}
//            System.out.println("methodToInvoke is " + methodToInvoke);
            obj = Reflection.callMethod(refObj, GameRefMethod);
        }
        Object[] argumentArray = objArgList.toArray(new Object[objArgList.size()]);
        return (GameObject) Reflection.callMethod(obj, methodToInvoke, argumentArray);
    }
    
    /**Takes in CurrentLevelID and CurrentSceneID to produce 
     * @param levelID
     * @param sceneID
     * @return the desired array parameter
     */
    private Object[] idSelector (String numArg) {
        Object objectID = objArgList.remove(0);
        Map<String,Object[]> argMap = new HashMap<String,Object[]>();
        argMap.put(GAME_STATE, new Object[]{objectID});
        argMap.put(LEVEL, new Object[]{myLevelID});
        argMap.put(OBJECT_ID, new Object[]{objectID});
        argMap.put(LEVEL_SCENE, new Object[]{myLevelID, mySceneID});
        argMap.put(LEVEL_SCENE_OBJECT,new Object[]{myLevelID, mySceneID, objectID});
        return argMap.get(numArg);
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