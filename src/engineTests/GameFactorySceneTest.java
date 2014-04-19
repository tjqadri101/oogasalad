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
import objects.Player;
import org.junit.Test;
import stage.Game;
import jgame.platform.StdGame;
import junit.framework.TestCase;
import engine.GameEngine;
import gameFactory.FactoryException;
import gameFactory.GameFactory;

/**
 * @Author: Steve (Siyang) Wang
 * tests the Scene 
 */
public class GameFactorySceneTest extends TestCase{
    
    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;
    protected NonPlayer myActor;
    protected Player myPlayer;
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
        
        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        myPlayer = (Player) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);   
    }
    
    @Test
    public void testDFParser() throws IndexOutOfBoundsException{

        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        List<Object> PARSED_OBJECT_LIST = Arrays.asList(PARSED_OBJECT_ARRAY);

        List<Object> parsedObjList = null;
        try {
            parsedObjList = (List<Object>) myFactory.parseOrder(CREATEPLAYER_OBJECT_LIST, (String) CREATEPLAYER_OBJECT_LIST.get(0)).get("Argument");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(parsedObjList, PARSED_OBJECT_LIST);
        assertEquals(parsedObjList.get(1),"actor_default.png");
    }
    
    @Test
    public void testCreateScene() throws FactoryException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateScene","ID",1,"ID",1};

        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(CREATELEVEL_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(2, myGame.getMyLevelMap().get(1).getMySceneMap().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    // SwitchScene done through Engine
    @Test
    public void testSwitchScene() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"SwitchScene","ID",1,"ID",0};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(0, myEngine.getCurrentSceneID());
    }
    
    @Test
    public void testDeleteScene() throws FactoryException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"DeleteScene","ID",1,"ID",1};

        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(CREATELEVEL_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getMyLevelMap().get(1).getMySceneMap().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    @Test
    public void testModifySceneBackground() throws FactoryException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"ModifyScene","ID",0,"Background","bg.png"};

        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(CREATELEVEL_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getMyLevelMap().get(1).getMySceneMap().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    @Test
    public void testModifyScenePlayerInitialP() throws FactoryException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"ModifyScene","ID",0,"PlayerInitialPosition",100.0,200.0};

        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(CREATELEVEL_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(100.0, myGame.getMyLevelMap().get(1).getMySceneMap().get(0).getPlayerInitPosition()[0]);

// here the levelID=1, SceneID=0, objID=0
    }

    

}