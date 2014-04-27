package engineTests;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import objects.NonPlayer;
import org.junit.Test;
import stage.Game;
import util.IParser;
import engine.GameEngine;
import gameFactory.GameFactory;
/**
 * @Author: Steve (Siyang) Wang
 * tests the Actor related order: creation and modification
 */
public class IParserTest extends TestCase{
    
    protected GameEngine myEngine;
    protected Game myGame;
    protected GameFactory myFactory;
    protected NonPlayer myActor;
    protected IParser p = new IParser();

    protected void setUp(){
        myGame = new Game();
        myEngine = new GameEngine(true);
        myEngine.setGame(myGame);
            myGame.addLevel(1);
            myGame.addScene(1, 0);
            myEngine.setCurrentScene(1, 0);
        myFactory = new GameFactory(myEngine);
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
    public void testParseParameter() throws IndexOutOfBoundsException{

        String UNPARSED_STRING = "CreatePlayer,ID,0,Image,actor_default.png,3,3," +
                        "Position,20.0,30.0,Name,myPlayer,CollisionID,0,Lives,1";
        
        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {0,"actor_default.png",3,3,
                                                       20.0,30.0,"myPlayer",0,1};

        List<Object> PARAMETER_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        List<Object> parsedObjList = null;
        try {
            parsedObjList = p.parseParameter(UNPARSED_STRING); 
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(parsedObjList, PARAMETER_LIST);
    }
    
    @Test
    public void testParseType() throws IndexOutOfBoundsException{

        String UNPARSED_STRING = "CreatePlayer,ID,0,Image,actor_default.png,3,3," +
                        "Position,20.0,30.0,Name,myActor,CollisionID,0,Lives,1";
        
        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"ID","Image","Position",
                                                       "Name","CollisionID","Lives"};

        List<Object> PARAMETER_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);

        List<String> parsedObjList = null;
        try {
            parsedObjList = p.parseType(UNPARSED_STRING); 
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(parsedObjList, PARAMETER_LIST);
    }

    
/* Will implement when the Game/Engine is ready
 *   
 *   @Test
    public void testDeleteActor() throws FactoryException{

        Object[] UNPARSED_OBJECT_ARRAY = new Object[] {"DeleteActor","ID",0};

        List<Object> CREATEPLAYER_OBJECT_LIST = Arrays.asList(UNPARSED_OBJECT_ARRAY);
        NonPlayer myObject = null;
        try {
            myObject = (NonPlayer) myFactory.processOrder(CREATEPLAYER_OBJECT_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        assertEquals(null, myGame.getNonPlayer(1, 0, 0));
    }*/

}