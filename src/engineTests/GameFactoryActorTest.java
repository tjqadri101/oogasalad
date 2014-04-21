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
import util.IParser;
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
    protected IParser p;

    protected void setUp(){
        myGame = new Game();
        myEngine = new GameEngine(true);
        myEngine.setGame(myGame);
            myGame.addLevel(1);
            myGame.addScene(1, 0);
            myEngine.setCurrentScene(1, 0);
        myFactory = new GameFactory(myEngine);
        p = new IParser();
        
////        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
//        String STRINGINPUT = "CreateActor,ID,0,ActorImage,actor_default.png,3,3," +
//        		"position,0.0,0.0,Name,myActor,CollisionID,0, Lives,1";
//        myActor = (NonPlayer) myFactory.processOrder(STRINGINPUT);
////        myActor = (NonPlayer) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);
//        String ORDER_TEST = "0,actor_default.png,3,3,20.0,30.0,myPlayer,0,1";
        
        String CREATE_ACTOR = "CreateActor,ID,0,Image,actor_default.png,3,3," +
                "Position,0.0,0.0,Name,myActor,CollisionID,0,Lives,1";
        
        myFactory.processOrder(CREATE_ACTOR);
    }
    
//    @Test
//    public void testDFParser() throws IndexOutOfBoundsException{
//
//        Object[] PARSED_PARAMETER_OBJECT = new Object[] {0, "actor_default.png",3,3,
//                                                     20.0, 30.0, "myActor", 0, 1}; 
//        List<Object> supposedResult = Arrays.asList(PARSED_PARAMETER_OBJECT);
//        
//        String STRINGINPUT = "CreateActor,ID,0,Image,actor_default.png,3,3," +
//                "Position,20.0,30.0,Name,myActor,CollisionID,0,Lives,1";
//        List<Object> result = null;
//        
//        try {
//            result = p.parseParameter(STRINGINPUT); 
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception");
//        }
//        assertEquals(supposedResult, result);
////        assertEquals(parsedObjList.get(1),"actor_default.png");
//    }
//  
//    @Test
//    public void testCreateActor() throws FactoryException{
//        String CREATE_ACTOR = "CreateActor,ID,0,Image,actor_default.png,3,3," +
//                "Position,0.0,0.0,Name,myActor,CollisionID,0,Lives,1";
//        
//        NonPlayer myObject = null;
//        try {
//            myObject = (NonPlayer) myFactory.processOrder(CREATE_ACTOR);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception");
//        }
//        assertEquals(myObject, myGame.getNonPlayer(1, 0, 0));
//// here the levelID=1, SceneID=0, objID=0
//    }
//
//    @Test
//    public void testModifyActorSpeed() throws FactoryException{
//        String MODIFY_ACTOR = "ModifyActor,ID,0,Speed,5.0,5.0";
//
//        try {
//            myFactory.processOrder(MODIFY_ACTOR);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception");
//        }
////        System.out.println("the speed in the speed test is " + myGame.getNonPlayer(1, 0, 0).xspeed);
//        assertEquals(5.0, myGame.getNonPlayer(1, 0, 0).xspeed);
//    }
//
//    @Test
//    public void testModifyActorChangetoID() throws FactoryException{
//        
//        String CHANGETOID_ORDER = "ModifyActor,ID,0,ChangeToID,1";
////        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeToID", 1};
//
////        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
//        try {
//            myFactory.processOrder(CHANGETOID_ORDER);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception");
//        }
//        assertEquals(1, myGame.getNonPlayer(1, 0, 0).getID());
//    }
//
//    @Test
//    public void testModifyActorChangeCollisionID() throws FactoryException{
//        String CHANGE_COLLISION_ID = "ModifyActor,ID,0,ChangeCollisionID,1";
////        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"ChangeCollisionID", 1};
////        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
//        try {
//            myFactory.processOrder(CHANGE_COLLISION_ID);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception");
//        }
//        assertEquals(1, myGame.getNonPlayer(1, 0, 0).colid);
//    }
//
//    @Test
//    public void testModifyActorPosition() throws FactoryException{
//        String CHANGE_POSITION = "ModifyActor,ID,0,Position,100.0,100.0";
////        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Position", 100.0, 100.0};
////        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
//        try {
//            myFactory.processOrder(CHANGE_POSITION);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception");
//        }
//        assertEquals(100.0, myGame.getNonPlayer(1, 0, 0).x);
//        assertEquals(100.0, myGame.getNonPlayer(1, 0, 0).y);
//    }
//    
//    @Test
//    public void testModifyActorSetMoveBehavior() throws FactoryException{
//        String SET_MOVE_BEHAVIOR = "ModifyActor,ID,0,RegularMove,RegularMove,10.0,10.0";
////        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"RegularMove","RegularMove", 10.0, 10.0};
////        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
//        try {
//            myFactory.processOrder(SET_MOVE_BEHAVIOR);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception");
//        }
//        assertEquals("RegularMove", myGame.getNonPlayer(1, 0, 0).getMyMoveBehavior());
////        assertEquals(10.0, myGame.getNonPlayer(1, 0, 0).getMyInitX());
//    }

    @Test
    public void testModifyActorCollisionBehavior() throws FactoryException{
        String SET_COL_BEHAVIOR = "ModifyActor,Colid,1,HitterEliminateVictim,HitterEliminateVictim,2";
//        Object[] UNPARSED_ORDER = new Object[] {"ModifyActor","ID",0,"Die","ShowCorpse"};
//        List<Object> MODIFYACTOR_OBJECT_LIST = Arrays.asList(UNPARSED_ORDER);
        try {
            myFactory.processOrder(SET_COL_BEHAVIOR);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        Set<String> set = (Set<String>) myGame.getNonPlayer(1, 0, 0).getMyCollisionBehavior().values();
        assert(set.contains("HitterEliminateVictim"));
    }

///* Will implement when the Game/Engine is ready
// *   
// *   @Test
//    public void testDeleteActor() throws FactoryException{
//
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"DeleteActor","ID",0};
//
//        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
//        NonPlayer myObject = null;
//        try {
//            myObject = (NonPlayer) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception");
//        }
//        assertEquals(null, myGame.getNonPlayer(1, 0, 0));
//    }*/
//    
//
////    Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateActor","ID",0,"ActorImage","actor_default.png",3,3,
////                                                   "position",0.0,0.0,"Name","myActor","CollisionID",0, "Lives",1};
//
    }