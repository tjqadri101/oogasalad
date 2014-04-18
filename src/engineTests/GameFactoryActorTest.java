package engineTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * tests the Actor related order: creation and modification
 */
public class GameFactoryActorTest extends TestCase{
    
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
        
        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateActor","ID",0,"ActorImage","actor_default.png",3,3,
                                                       "position",0.0,0.0,"Name","myActor","CollisionID",0, "Lives",1};
        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        myActor = (NonPlayer) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);
    }
    
    @Test
    public void testDFParser() throws IndexOutOfBoundsException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreatePlayer","ID", 7, "Image", "actor_default.png",3,3,
                                                       "position", 20.0, 30.0, "Name", "myPlayer", "CollisionID", 0, "Lives",1};
        Object[] PARSED_OBJECT_ARRAY = new Object[] {7, "actor_default.png",3,3,
                                                     20.0, 30.0, "myPlayer", 0, 1};
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
    public void testCreateActor() throws FactoryException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateActor","ID",0,"ActorImage","actor_default.png",3,3,
                                                       "position",0.0,0.0,"Name","myActor","CollisionID",0, "Lives",1};

        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        NonPlayer myObject = null;
        try {
            myObject = (NonPlayer) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(myObject, myGame.getNonPlayer(1, 0, 0));
// here the levelID=1, SceneID=0, objID=0
    }
    
    @Test
    public void testModifyActorSpeed() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Speed", 5.0, 5.0};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        System.out.println("the speed in the speed test is " + myGame.getNonPlayer(1, 0, 0).xspeed);
        assertEquals(5.0, myGame.getNonPlayer(1, 0, 0).xspeed);
    }
    
    @Test
    public void testModifyActorChangetoID() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeToID", 1};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getNonPlayer(1, 0, 0).getID());
    }
    
    @Test
    public void testModifyActorChangeCollisionID() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeCollisionID", 1};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getNonPlayer(1, 0, 0).colid);
    }
    
    @Test
    public void testModifyActorPosition() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Position", 100.0, 100.0};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(100.0, myGame.getNonPlayer(1, 0, 0).x);
        assertEquals(100.0, myGame.getNonPlayer(1, 0, 0).y);
    }
    
    @Test
    public void testModifyActorMove() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Move","RegularMove", 10.0, 10.0};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals("RegularMove", myGame.getNonPlayer(1, 0, 0).getMyMoveBehavior());
        assertEquals(10.0, myGame.getNonPlayer(1, 0, 0).getMySetXSpeed());
    }
    
    @Test
    public void testModifyActorDie() throws FactoryException{
        
        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Die","ShowCorpse"};

        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFYACTOR_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals("ShowCorpse", myGame.getNonPlayer(1, 0, 0).getMyDieBehavior());
    }

}