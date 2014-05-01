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
public class GameFactoryLevelTest extends TestCase{
    
    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;
    protected NonPlayer myActor;
    protected static final Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreatePlayer","ID",0,"PlayerImage","actor_default.png",3,3,
                                                                          "position",20.0,30.0,"Name","myPlayer","CollisionID",0, "Lives",1};
    protected static final Object[] PARSED_OBJECT_ARRAY = new Object[] {0, "actor_default.png",3,3,
                                                                      20.0, 30.0, "myPlayer", 0, 1};

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
    public void testCreateLevel() throws FactoryException{
        String CREATE_LEVEL = "CreateLevel,ID,2";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateLevel","ID",2};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(CREATE_LEVEL);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(2, myGame.getMyLevelMap().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    @Test
    public void testSetInitialScene() throws FactoryException{
        String SET_INITIAL_SCENE = "ModifyLevel,ID,1,SetInitialScene,0";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateLevel","ID",2};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(SET_INITIAL_SCENE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(2, myGame.getMyLevelMap().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
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
//        assertEquals(1, myGame.getMyLevelMap().size());
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
//        Set<Integer> keys = myGame.getMyLevelMap().keySet();
//        assertTrue(keys.contains(3));
    }
}