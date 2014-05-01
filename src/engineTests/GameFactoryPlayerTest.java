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
import util.IParser;
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
    IParser p = new IParser();
    protected static final Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreatePlayer","ID",0,"PlayerImage","actor_default.png",3,3,
                                                                          "position",20.0,30.0,"Name","myPlayer","CollisionID",0, "Lives",1};
    protected static final Object[] PARSED_OBJECT_ARRAY = new Object[] {0, "actor_default.png",3,3,
                                                                      20.0, 30.0, "myPlayer", 0, 1};
    protected static final String UNPARSED_STRING = "CreatePlayer,ID,0,Image,actor_default.png,3,3," +
            "Position,0.0,0.0,Name,myActor,CollisionID,0,Lives,1";

    
    protected void setUp(){
        myGame = new Game();
        myEngine = new GameEngine(true);
        myEngine.setGame(myGame);
            myGame.addLevel(1);
            myGame.addScene(1, 0);
            myEngine.setCurrentScene(1, 0);
        myFactory = new GameFactory(myEngine);

        myPlayer = (Player) myFactory.processOrder(UNPARSED_STRING);
    }
    
    @Test
    public void testParseAll() throws IndexOutOfBoundsException{

        String UNPARSED_STRING = "CreatePlayer,ID,0,Image,actor_default.png,3,3," +
                        "Position,20.0,30.0,Name,myActor,CollisionID,0,Lives,1";
        
        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreatePlayer","ID",0,"Image","actor_default.png",3,3,
                                                       "Position",20.0,30.0,"Name","myActor","CollisionID",0,"Lives",1};

        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        List<Object> parsedObjList = null;
        try {
            parsedObjList = p.parseAll(UNPARSED_STRING); 
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(parsedObjList, CREATEPLAYER_OBJECT_LIST);
    }
    
    
    @Test
    public void testCreatePlayer() throws FactoryException{
//        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        Player myObject = null;
        try {
            myObject = (Player) myFactory.processOrder(UNPARSED_STRING);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(myObject, myGame.getPlayer(0));
// here the levelID=1, SceneID=0, objID=0
    }
    
    @Test
    public void testCanMoveInAir() throws FactoryException{
        String MOVE_INAIR = "ModifyPlayer,ID,0,CanMoveInAir,true";
        
        try {
            myFactory.processOrder(MOVE_INAIR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(myObject, myGame.getNonPlayer(1, 0, 0));
    }
    /*
    @Test
    public void testModifyPlayerChangetoID() throws FactoryException{
        String CHANGETOID_ORDER = "ModifyPlayer,ID,0,ChangeToID,1";
//        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeToID", 1};

//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(CHANGETOID_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getPlayer(0).getID());
    }
    
    @Test
    public void testModifyPlayerImage() throws FactoryException{
        String MODIFY_IMAGE = "ModifyPlayerImage,ID,0,Image,actor_default.png,10,10";
//        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        myPlayer = (Player) myFactory.processOrder(UNPARSED_STRING);
//        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ShowCorpse","ShowCorpse","imageURL",10,10,400};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFY_IMAGE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals("actor_default.png", myGame.getPlayer(0).getMyGfx());
    }
    
    @Test
    public void testModifyPlayerChangeCollisionID() throws FactoryException{
        String CHANGETOID_ORDER = "ModifyPlayer,ID,0,ChangeToID,1";
//        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ChangeCollisionID", 1};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(CHANGETOID_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getPlayer(0).getID());
    }

    @Test
    public void testModifyPlayerPosition() throws FactoryException{
        String CHANGE_POSITION = "ModifyPlayer,ID,0,Position,100.0,100.0";
//        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"Position", 100.0, 100.0};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(CHANGE_POSITION);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(100.0, myGame.getPlayer(0).x);
        assertEquals(100.0, myGame.getPlayer(0).y);
    }

    @Test
    public void testModifyPlayerSetMoveBehavior() throws FactoryException{
        String SET_MOVE_BEHAVIOR = "ModifyPlayer,ID,0,RegularMove,RegularMove,10.0,10.0";
//        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"RegularMove","RegularMove", 10.0, 10.0};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(SET_MOVE_BEHAVIOR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals("RegularMove", myGame.getPlayer(0).getMyMoveBehavior());
// TODO: below problematic because the move() is not called, then the xSpeed or ySpeed might not get updated.
//        see result in engine
//        assertEquals(10.0, myGame.getPlayer(0).getMyInitX());
    }

    // image url not provided...
    @Test
    public void testModifyPlayerDie() throws FactoryException{
        String REGULAR_DIE = "ModifyPlayer,ID,0,RegularRemove,RegularRemove";
//        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        myPlayer = (Player) myFactory.processOrder(UNPARSED_STRING);
//        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ShowCorpse","ShowCorpse","imageURL",10,10,400};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(REGULAR_DIE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals("RegularRemove", myGame.getPlayer(0).getMyDieBehavior());
    }
//   
    */
}