package engineTests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import engine.GameEngine;
import engineManagers.CollisionManager;
import gameFactory.FactoryException;
import gameFactory.GameFactory;
import objects.NonPlayer;
import org.junit.Test;
import stage.Game;
import util.IParser;

public class GameFactoryGravityTest extends TestCase{

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
        
//        myFactory.processOrder(CREATE_ACTOR);
        
//        String STRINGINPUT = "CreateActor,ID,0,ActorImage,actor_default.png,3,3," +
//                "position,0.0,0.0,Name,myActor,CollisionID,0, Lives,1";
//        myActor = (NonPlayer) myFactory.processOrder(STRINGINPUT);
    }
    
    @Test
    public void testModifyGravity() throws FactoryException{
        System.out.println("the Gravity is called");
        
        String MODIFY_GRAVITY= "ModifyGravity,Magnitude,0.1";

        try {
            myFactory.processOrder(MODIFY_GRAVITY);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        System.out.println("the speed in the speed test is " + myGame.getNonPlayer(1, 0, 0).xspeed);
        assertEquals(0.1, myGame.getGravity().getMagnitude());
    }
    
}

