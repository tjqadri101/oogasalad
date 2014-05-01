package engineTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import jgame.platform.JGEngine;
import objects.GameObject;
import objects.NonPlayer;
import org.junit.Test;
import stage.Game;
import jgame.platform.StdGame;
import junit.framework.TestCase;
import engine.GameEngine;
import gameFactory.FactoryException;
import gameFactory.GameFactory;
/**
 * @Author: Steve (Siyang) Wang
 * tests the create
 */
public class GameFactoryManagerTest extends TestCase{
    
    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;
    protected NonPlayer myActor;

    protected void setUp(){
        myGame = new Game();
        myEngine = new GameEngine(true);
        myEngine.setGame(myGame);
            myGame.addLevel(1);
            myGame.addScene(1, 0);
            myEngine.setCurrentScene(1, 0);
        myFactory = new GameFactory(myEngine);
    }
   
    @Test
    public void testTEMSetTriggerByTime() throws FactoryException{
        String CREATE_LEVEL = "ModifyTriggerEventManager,ID,1,TriggerByTime,SetTriggerByTime,40";
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
        String CREATE_LEVEL = "ModifyTriggerEventManager,ID,1,TriggerByRemove,SetTriggerByRemove,0";
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
    public void testTEMTriggerByTileCollision() throws FactoryException{
        String CREATE_LEVEL = "ModifyTriggerEventManager,ID,4,TriggerByTileCollision,TileCollision,1,20,60,10,10";
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
    /*
     * 
    @Test
    public void testDeleteLevel() throws FactoryException{
        String DELETE_LEVEL = "DeleteLevel,ID,2";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"DeleteLevel","ID",2};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(DELETE_LEVEL);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getMyLevelMap().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    @Test
    public void testResetLevelID() throws FactoryException{
        String RESET_LEVEL = "ResetLevelID,ID,1,ID,3";
//        Object[] UNPARSED_ORDER = new Object[] {"ResetLevelID","ID",1,"ID", 3};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(RESET_LEVEL);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        Set<Integer> keys = myGame.getMyLevelMap().keySet();
        assertTrue(keys.contains(3));
    }*/
}