package engineTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import jgame.platform.JGEngine;
import objects.GameObject;
import objects.NonPlayer;
import objects.Player;
import org.junit.Test;
import saladConstants.SaladConstants;
import stage.Game;
import util.IParser;
import util.SaladUtil;
import jgame.platform.StdGame;
import junit.framework.TestCase;
import engine.GameEngine;
import engineManagers.CollisionManager;
import gameFactory.FactoryException;
import gameFactory.GameFactory;

/**
 * @Author: Steve (Siyang) Wang
 * tests the Scene 
 */
public class TriggerManagerTest extends TestCase{
    
    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;
    protected NonPlayer myActor;
    protected Player myPlayer;
    protected IParser p;
    protected CollisionManager cm;
//    private static final String CREATE_ACTOR = "CreateActor,ID,0,Image,actor_default.png,3,3," +
//            "Position,0.0,0.0,Name,myActor,CollisionID,0,Lives,1";
//    protected static final Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreatePlayer","ID",0,"PlayerImage","actor_default.png",3,3,
//                                                                          "position",20.0,30.0,"Name","myPlayer","CollisionID",0, "Lives",1};
//    protected static final Object[] PARSED_OBJECT_ARRAY = new Object[] {0, "actor_default.png",3,3,
//                                                                      20.0, 30.0, "myPlayer", 0, 1};

    protected void setUp(){
        myGame = new Game();
        myEngine = new GameEngine(true);
        myEngine.setGame(myGame);
            myGame.addLevel(1);
            myGame.addScene(1, 0);
            myEngine.setCurrentScene(1, 0);
        myFactory = new GameFactory(myEngine);
        p = new IParser();
        cm = new CollisionManager();
//        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
//        myActor = (NonPlayer) myFactory.processOrder(CREATE_ACTOR);   
    }
    
    @Test
    public void testDoEvent() throws FactoryException{
        
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateScene","ID",1,"ID",1};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(CREATE_SCENE);

        try {
            myGame.getTriggerManager().setEventOrTriggerBehavior(1, "TriggerByTime", 200);
            myGame.getTriggerManager().setEventOrTriggerBehavior(1, "EventEnemyShower", 5, "actor_default.png");
            myGame.getTriggerManager().performEvent(myEngine, 1);
            /*private void doEvent (GameEngine myEngine, int etPairID) {
                List<Object> rawPara = myEventMap.get(etPairID);
                int size = rawPara.size();
//                eventParameter.remove(0);
                List<Object> eventParameter = rawPara.subList(1, size);
                System.out.println("doEvent: the eventParameter is " + eventParameter);
                String eventBehavior = (String) rawPara.get(0);
                System.out.println("doEvent: eventBehavior is " + eventBehavior);
                ResourceBundle behaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
                                   + SaladConstants.OBJECT_BEHAVIOR);
                SaladUtil.behaviorReflection(behaviors, eventBehavior, eventParameter, DO_EVENT, myEngine);*/
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
/*        System.out.println(myGame.getTriggerManager().getTriggerMap().get(1));
        System.out.println(myGame.getTriggerManager().getTriggerMap().get(1).get(0));
        assertEquals("TriggerByTime", myGame.getTriggerManager().getTriggerMap().get(1).get(0));*/
    }
    
    
    @Test
    public void testTEMTriggerByCollision() throws FactoryException{
        String CREATE_LEVEL = "ModifyTriggerEventManager,ID,2,TriggerByCollision,Collision,1,2,3";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateLevel","ID",2};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(CREATE_LEVEL);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(2, myGame.getTEM().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    
    @Test
    public void testTEMTriggerByRemove() throws FactoryException{
        String CREATE_LEVEL = "ModifyTriggerEventManager,ID,3,TriggerByRemove,TriggerByRemove,0";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateLevel","ID",2};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(CREATE_LEVEL);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(2, myGame.getTEM().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    
    @Test
    public void testTEMSetTriggerByTime() throws FactoryException{
        String CREATE_LEVEL = "ModifyTriggerEventManager,ID,1,TriggerByTime,TriggerByTime,40";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateLevel","ID",2};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(CREATE_LEVEL);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(2, myGame.getTEM().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    
    @Test
    public void testSetTrigger() throws FactoryException{

//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateScene","ID",1,"ID",1};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(CREATE_SCENE);

        try {
            myGame.getTriggerManager().setEventOrTriggerBehavior(1, "TriggerByTime", 200);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        System.out.println(myGame.getTriggerManager().getTriggerMap().get(1));
        System.out.println(myGame.getTriggerManager().getTriggerMap().get(1).get(0));
        assertEquals("TriggerByTime", myGame.getTriggerManager().getTriggerMap().get(1).get(0));
    }
    

}
     