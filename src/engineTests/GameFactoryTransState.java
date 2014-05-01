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
public class GameFactoryTransState extends TestCase{
    
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
    public void testTSBackground() throws FactoryException{
        String TSBackground = "ModifyTransitionState,GameState,Title,TSBackground,actor_default.png";
//        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateScene","ID",1,"ID",1};
//        List<Object> CREATELEVEL_OBJECT_LIST = Arrays.asList(CREATE_SCENE);

        try {
            myFactory.processOrder(TSBackground);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
//        assertEquals(2, myGame.getLevelMap().get(1).getMySceneMap().size());
// here the levelID=1, SceneID=0, objID=0
    }
    
}