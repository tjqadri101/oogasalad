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
 * tests the Actor related order: creation and modification
 */
public class GameFactoryActorTest extends TestCase{

    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;
    protected NonPlayer myActor;
    protected IParser p;
    protected CollisionManager cm;
    private static final String CREATE_ACTOR = "CreateActor,ID,0,Image,actor_default.png,3,3," +
            "Position,0.0,0.0,Name,myActor,CollisionID,0,Lives,1";

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

        ////        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        ////        myActor = (NonPlayer) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);
        //        String ORDER_TEST = "0,actor_default.png,3,3,20.0,30.0,myPlayer,0,1";

        myFactory.processOrder(CREATE_ACTOR);

        //        String STRINGINPUT = "CreateActor,ID,0,ActorImage,actor_default.png,3,3," +
        //                "position,0.0,0.0,Name,myActor,CollisionID,0, Lives,1";
        //        myActor = (NonPlayer) myFactory.processOrder(STRINGINPUT);
    }

    
    @Test
    public void testCreateActor() throws FactoryException{
        String CREATE_ACTOR = "CreateActor,ID,0,Image,actor_default.png,3,3," +
                "Position,0.0,0.0,Name,myActor,CollisionID,0,Lives,1";

        NonPlayer myObject = null;
        try {
            myObject = (NonPlayer) myFactory.processOrder(CREATE_ACTOR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(myObject, myGame.getNonPlayer(1, 0, 0));
        // here the levelID=1, SceneID=0, objID=0
    }

    @Test
    public void testModifyActorImage() throws FactoryException{
        String MODIFY_IMAGE = "ModifyActorImage,ID,0,ActorImage,actor_default.png,10,10";
        //        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        myActor = (NonPlayer) myFactory.processOrder(CREATE_ACTOR);
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ShowCorpse","ShowCorpse","imageURL",10,10,400};
        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(MODIFY_IMAGE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals("actor_default.png", myGame.getNonPlayer(1,0,0).getMyGfx());
    }

    @Test
    public void testModifyActorSpeed() throws FactoryException{
        String MODIFY_ACTOR = "ModifyActor,ID,0,Speed,5.0,5.0";

        try {
            myFactory.processOrder(MODIFY_ACTOR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        //        System.out.println("the speed in the speed test is " + myGame.getNonPlayer(1, 0, 0).xspeed);
        //        assertEquals(5.0, myGame.getNonPlayer(1, 0, 0).xspeed);
    }

    @Test
    public void testModifyActorChangetoID() throws FactoryException{

        String CHANGETOID_ORDER = "ModifyActor,ID,0,ChangeToID,1";
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeToID", 1};

        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(CHANGETOID_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getNonPlayer(1, 0, 0).getID());
    }

    @Test
    public void testModifyActorChangeCollisionID() throws FactoryException{
        String CHANGE_COLLISION_ID = "ModifyActor,ID,2,ChangeCollisionID,3";
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeCollisionID", 1};
        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(CHANGE_COLLISION_ID);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(1, myGame.getNonPlayer(1, 0, 0).colid);
    }

    @Test
    public void testModifyActorPosition() throws FactoryException{
        String CHANGE_POSITION = "ModifyActor,ID,0,Position,100.0,100.0";
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Position", 100.0, 100.0};
        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(CHANGE_POSITION);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(100.0, myGame.getNonPlayer(1, 0, 0).x);
        assertEquals(100.0, myGame.getNonPlayer(1, 0, 0).y);
    }

    @Test
    public void testModifyActorSetMoveBehavior() throws FactoryException{
        String SET_MOVE_BEHAVIOR = "ModifyActor,ID,0,RegularMove,RegularMove,10.0,10.0";
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"RegularMove","RegularMove", 10.0, 10.0};
        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(SET_MOVE_BEHAVIOR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        //        assertEquals("RegularMove", myGame.getNonPlayer(1, 0, 0).getActionManager().getMoveBehavior());
        //        assertEquals(10.0, myGame.getNonPlayer(1, 0, 0).getMyInitX());
    }

    @Test
    public void testDeleteActor() throws FactoryException{
        String DELETE_ACTOR = "DeleteActor,ID,0";
        //        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"DeleteActor","ID",0};
        //        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        NonPlayer myObject = null;
        try {
            myObject = (NonPlayer) myFactory.processOrder(DELETE_ACTOR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(null, myGame.getNonPlayer(1, 0, 0));
    }

    // Problem: if called when there is only one object, return error
    @Test
    public void testModifyCollisionBehavior() throws FactoryException{
        String SET_COL_BEHAVIOR = "ModifyCollisionBehavior,Colid,1,HitterEliminateVictim,HitterEliminateVictim,2,Up";
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Die","ShowCorpse"};
        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(SET_COL_BEHAVIOR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        Set<int[]> set = cm.getCollisionPair();
        assert(set.contains(new int[]{1,2}));
    }

    @Test
    public void testModifyTileCollisionBehavior() throws FactoryException{
        String SET_COL_BEHAVIOR = "ModifyTileCollisionBehavior,Colid,1,StayOnTile,StayOnTile,2,Up";
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Die","ShowCorpse"};
        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(SET_COL_BEHAVIOR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        Set<int[]> set = cm.getTileCollisionPair();
        assert(set.contains(new int[]{1,2}));
    }

    @Test
    public void testModifyActorDie() throws FactoryException{
        String REGULAR_DIE = "ModifyActor,ID,0,RegularRemove,RegularRemove";
        String ACTOR_DIE = "ModifyActor,ID,0,ShowCorpse,ShowCorpse,mushroom.png,10,10,400";
        //        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        myActor = (NonPlayer) myFactory.processOrder(CREATE_ACTOR);
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ShowCorpse","ShowCorpse","imageURL",10,10,400};
        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(REGULAR_DIE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        //        List<Object> set = myGame.getNonPlayer(1,0,0).getActionManager().getDieBehavior();
        //        assert(set.contains("RegularRemove"));
    }

    @Test
    public void testModifyActorImmobile() throws FactoryException{
        String Immobile = "ModifyActor,ID,0,Immobile,Immobile";
        //        String ACTOR_DIE = "ModifyActor,ID,0,ShowCorpse,ShowCorpse,mushroom.png,10,10,400";
        //        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        myActor = (NonPlayer) myFactory.processOrder(CREATE_ACTOR);
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyPlayer","ID",0,"ShowCorpse","ShowCorpse","imageURL",10,10,400};
        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(Immobile);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        //        List<Object> set = myGame.getNonPlayer(1,0,0).getActionManager().getDieBehavior();
        //        assert(set.contains("RegularRemove"));
    }
    
    @Test
    public void testSetInitBlood() throws FactoryException{

        String CHANGETOID_ORDER = "ModifyActor,ID,0,SetInitBlood,5";
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeToID", 1};

        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(CHANGETOID_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        //        assertEquals(1, myGame.getNonPlayer(1, 0, 0).getID());
    }
    
    @Test
    public void testRegularRemove() throws FactoryException{

        String CHANGETOID_ORDER = "ModifyActor,ID,0,RegularRemove,RegularRemove";
        //        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeToID", 1};

        //        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(CHANGETOID_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        //        assertEquals(1, myGame.getNonPlayer(1, 0, 0).getID());
    }

}