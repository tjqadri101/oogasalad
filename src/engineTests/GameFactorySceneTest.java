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
import util.IParser;
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
public class GameFactorySceneTest extends TestCase{
    
    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;
    protected NonPlayer myActor;
    protected Player myPlayer;
    protected IParser p;
    protected CollisionManager cm;
    private static final String CREATE_ACTOR = "CreateActor,ID,0,Image,actor_default.png,3,3," +
            "Position,0.0,0.0,Name,myActor,CollisionID,0,Lives,1";
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
        myActor = (NonPlayer) myFactory.processOrder(CREATE_ACTOR);   
    }
    
    @Test
    public void testCreateScene() throws FactoryException{
        String CREATE_SCENE = "CreateScene,ID,1,ID,1";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateScene","ID",1,"ID",1};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(CREATE_SCENE);

        try {
            myFactory.processOrder(CREATE_SCENE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(2, myGame.getLevelMap().get(1).getMySceneMap().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
    // SwitchScene done through Engine
    @Test
    public void testSwitchToNewLevel() throws FactoryException{
        String SWITCH_SCENE = "ModifyGame,SwitchSceneToNewLevelID,1,2,2";
//        Object[] UNPARSED_ORDER = new Object[] {"SwitchScene","ID",1,"ID",0};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(SWITCH_SCENE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(0, myEngine.getCurrentSceneID());
    }

    // SwitchScene done through Engine
    @Test
    public void testSwitchScene() throws FactoryException{
        String SWITCH_SCENE = "SwitchScene,ID,1,ID,0";
//        Object[] UNPARSED_ORDER = new Object[] {"SwitchScene","ID",1,"ID",0};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(SWITCH_SCENE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(0, myEngine.getCurrentSceneID());
    }

    @Test
    public void testDeleteScene() throws FactoryException{
        String DELETE_SCENE = "DeleteScene,ID,1,ID,1";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"DeleteScene","ID",1,"ID",1};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        try {
            myFactory.processOrder(DELETE_SCENE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(1, myGame.getMyLevelMap().get(1).getMySceneMap().size());
// here the levelID=1, SceneID=0, objID=0
    }

//    *//**
//     * Need to re-check this
//     * @throws FactoryException
//     *//*
    @Test
    public void testModifySceneBackground() throws FactoryException{
        String MODIFY_BKGD = "ModifySceneView,Background,devil.png,true,true,800,600";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"ModifyScene","ID",0,"Background","bg.png"};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        try {
            myFactory.processOrder(MODIFY_BKGD);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals("bg.png", myGame.getMyLevelMap().get(1).getMySceneMap().get(0).getBackgroundImage());
// here the levelID=1, SceneID=0, objID=0
    }

    @Test
    public void testModifyScenePlayerInitialP() throws FactoryException{
        String SCENE_PLAYERPOS = "ModifyScene,ID,0,PlayerInitialPosition,100.0,200.0";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"ModifyScene","ID",0,"PlayerInitialPosition",100.0,200.0};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        try {
            myFactory.processOrder(SCENE_PLAYERPOS);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(100.0, myGame.getMyLevelMap().get(1).getMySceneMap().get(0).getPlayerInitPosition()[0]);

// here the levelID=1, SceneID=0, objID=0
    }
}