package engineTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jgame.platform.JGEngine;
import objects.GameObject;
import objects.NonPlayer;
import objects.Player;
import org.junit.Test;
import stage.Game;
import jgame.platform.StdGame;
import engine.GameEngine;
import gameFactory.FactoryException;
import gameFactory.GameFactory;
import junit.framework.*;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

/**
 * @Author: Steve (Siyang) Wang
 * tests the functionality related to Player, including creation and modification
 */

public class GameFactoryPlayerTest extends TestCase{
    
    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;
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
    public void testCreatePlayer() throws FactoryException{

        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        Player myObject = null;
        try {
            myObject = (Player) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(myObject, myGame.getPlayer(0));
// here the levelID=1, SceneID=0, objID=0
    }

    // image url not provided...
    @Test
    public void testModifyPlayerDie() throws FactoryException{
        
        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        myPlayer = (Player) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);

        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ShowCorpse","ShowCorpse","imageURL",10,10,400};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals("ShowCorpse", myGame.getPlayer(0).getMyDieBehavior());
    }
    
    @Test
    public void testModifyPlayerChangetoID() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ChangeToID", 1};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getPlayer(0).getID());
    }
    
    @Test
    public void testModifyPlayerChangeCollisionID() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ChangeCollisionID", 1};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getPlayer(0).colid);
    }
    
    @Test
    public void testModifyPlayerPosition() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"Position", 100.0, 100.0};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(100.0, myGame.getPlayer(0).x);
        assertEquals(100.0, myGame.getPlayer(0).y);
    }
    
    @Test
    public void testModifyPlayerMove() throws FactoryException{

        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"RegularMove","RegularMove", 10.0, 10.0};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals("RegularMove", myGame.getPlayer(0).getMyMoveBehavior());
        assertEquals(10.0, myGame.getPlayer(0).getMyInitX());
    }
    
    
}