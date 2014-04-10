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
import engine.GameEngine;
import gameFactory.FactoryException;
import gameFactory.GameFactory;

public class GameFactoryTest {
    public static final String CREATEPLAYER_STRINGORDER = "CreatePlayer,ID,1,Image,actor_default," +
    		                                     "Position,20.0,30.0,Name,myPlayer,CollisionID,0";
    public static final String BACKGROUND_ORDER = "CreatePlayer,ID, 1,Position,20.0,30.0";
    public static final Object[] UNPARSED_OBJECT_INPUT = new Object[] {"CreatePlayer","ID", 7, "Image", "actor_default",
                                                                       "position", 20.0, 30.0, "Name", "myPlayer", "CollisionID", 0};
    public static final Object[] PARSED_OBJECT_INPUT = new Object[] {7, "actor_default", 20.0, 30.0, "myPlayer", 0};
    
//    public static final List<E> GENERIC_INPUT = new List<E>();
    
/*    @Test
    public void directCreateActorTest() throws FactoryException{
        GameEngine myEngine = new GameEngine();  
        Game myGame = new Game();
        GameFactory factory = new GameFactory(myEngine);
        Object[] UNPARSED_OBJECT_INPUT = new Object[] {"CreatePlayer","ID", 7, "Image", "actor_default",
                                                       "position", 20.0, 30.0, "Name", "myPlayer", "CollisionID", 0};
        Object[] PARSED_OBJECT_INPUT = new Object[] {7, "actor_default",
                                                     20.0, 30.0, "myPlayer", 0};
        List<Object> CREATEPLAYER_LISTINPUT = Arrays.asList(UNPARSED_OBJECT_INPUT);
        List<Object> PARSED_LISTINPUT = Arrays.asList(PARSED_OBJECT_INPUT);
        
        GameObject myObject = null;
        try {
            myObject = factory.EngineReflect(1, 1, PARSED_LISTINPUT, "createPlayer", myGame, null, "0");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(myObject, myEngine.createPlayer(7, "actor_default", 10.0, 20.0, "myPlayer", 0));
    }*/
    
    @Test
    public void createPlayerTest() throws FactoryException{
        GameEngine myEngine = new GameEngine();        
        Game myGame = new Game();
        GameFactory factory = new GameFactory(myEngine);
        
        Object[] UNPARSED_OBJECT_INPUT = new Object[] {"CreatePlayer","ID",7,"Image","actor_default",
                                                       "position",20.0,30.0,"Name","myPlayer","CollisionID",0};
        
        List<Object> CREATEPLAYER_LISTINPUT = Arrays.asList(UNPARSED_OBJECT_INPUT);
        GameObject myObject = null;
        try {
            myObject = factory.processOrder(CREATEPLAYER_LISTINPUT);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(myObject, myEngine.createPlayer(7, "actor_default", 10.0, 20.0, "myPlayer", 0));
    }
    /*
    @Test
    public void createActorTest() throws FactoryException{
        Game myGame = new Game();
        GameEngine myEngine = new GameEngine();        
        GameFactory factory = new GameFactory(myEngine);
        Object[] UNPARSED_OBJECT_INPUT = new Object[] {"CreatePlayer","ID", 7, "Image", "actor_default",
                                                       "position", 20.0, 30.0, "Name", "myPlayer", "CollisionID", 0};
        List<Object> CREATEPLAYER_LISTINPUT = Arrays.asList(UNPARSED_OBJECT_INPUT);
        GameObject myObject = null;
        try {
            myObject = factory.processOrder(CREATEPLAYER_LISTINPUT);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(myObject, myEngine.createPlayer(7, "actor_default", 10.0, 20.0, "myPlayer", 0));
    }*/
    
    @Test
    public void DFParserTest() throws IndexOutOfBoundsException{
        Game myGame = new Game();
        GameEngine myEngine = new GameEngine();        
        GameFactory factory = new GameFactory(myEngine);
        Object[] UNPARSED_OBJECT_INPUT = new Object[] {"CreatePlayer","ID", 7, "Image", "actor_default",
                                                       "position", 20.0, 30.0, "Name", "myPlayer", "CollisionID", 0};
        Object[] PARSED_OBJECT_INPUT = new Object[] {7, "actor_default",
                                                     20.0, 30.0, "myPlayer", 0};
        List<Object> PARSED_LISTINPUT = Arrays.asList(PARSED_OBJECT_INPUT);
        List<Object> CREATEPLAYER_LISTINPUT = Arrays.asList(UNPARSED_OBJECT_INPUT);
        
        List<Object> parsedObjList = null;
        try {
            parsedObjList = factory.parseOrder(CREATEPLAYER_LISTINPUT, (String) CREATEPLAYER_LISTINPUT.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(parsedObjList, PARSED_LISTINPUT);
        assertEquals(parsedObjList.get(1),"actor_default");
    }
    
    
}
