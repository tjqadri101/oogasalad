package engineTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jgame.platform.JGEngine;
import objects.GameObject;
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
public class GameFactoryTest extends TestCase{
    
    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;


    protected void setUp(){
        myGame = new Game();
        myEngine = new GameEngine();
        myEngine.setGame(myGame);
            myGame.addLevel(1);
            myGame.addScene(1, 0);
            myEngine.setCurrentScene(0);
            myEngine.setCurrentLevel(1);
            
        myFactory = new GameFactory(myEngine);
    }

    @Test
    public void testCreateActor() throws FactoryException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreateActor","ID",0,"Image","actor_default.png",
                                                       "position",0.0,0.0,"Name","myActor","CollisionID",0};

        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        GameObject myObject = null;
        try {
            myObject = myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(myObject, myGame.getGameObject(1, 0, 0));

    }
    @Test
    public void testDFParser() throws IndexOutOfBoundsException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"CreatePlayer","ID", 7, "Image", "actor_default.png",
                                                       "position", 20.0, 30.0, "Name", "myPlayer", "CollisionID", 0};
        Object[] PARSED_OBJECT_ARRAY = new Object[] {7, "actor_default.png",
                                                     20.0, 30.0, "myPlayer", 0};
        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        List<Object> PARSED_OBJECT_LIST = Arrays.asList(PARSED_OBJECT_ARRAY);

        List<Object> parsedObjList = null;
        try {
            parsedObjList = myFactory.parseOrder(CREATEPLAYER_OBJECT_LIST, (String) CREATEPLAYER_OBJECT_LIST.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(parsedObjList, PARSED_OBJECT_LIST);
        assertEquals(parsedObjList.get(1),"actor_default.png");
    }
    
    
    /* For reference only 
     * 
    public static final String CREATEPLAYER_STRINGORDER = "CreatePlayer,ID,1,Image,actor_default.png," +
            "Position,20.0,30.0,Name,myPlayer,CollisionID,0";
    public static final String BACKGROUND_ORDER = "CreatePlayer,ID, 1,Position,20.0,30.0";
    public static final Object[] UNPARSED_OBJECT_INPUT = new Object[] {"CreatePlayer","ID", 7, "Image", "actor_default.png",
                                                                       "position", 20.0, 30.0, "Name", "myPlayer", "CollisionID", 0};
    public static final Object[] PARSED_OBJECT_INPUT = new Object[] {7, "actor_default.png", 20.0, 30.0, "myPlayer", 0};
*/    

}