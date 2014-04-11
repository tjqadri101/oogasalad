package engineTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jgame.platform.JGEngine;
import objects.GameObject;
import org.junit.Test;
import stage.Game;
import jgame.platform.StdGame;
import engine.GameEngine;
import gameFactory.FactoryException;
import gameFactory.GameFactory;

public class FactoryTest {
    public static final String CREATE_PLAYER_ORDER = "CreatePlayer,ID,1,Position,100,200";
    public static final String CREATE_ACTOR_ORDER = "CreateActor,ID,1,Position,100,200";
    public static final String BACKGROUND_ORDER = "CreatePlayer,ID, 1,Position,100,200";
    public static final Object[] UNPARSED_OBJECT_INPUT = new Object[] {"ID", 7, "position", 200.0, 300.0};
    public static final Object[] PARSED_OBJECT_INPUT = new Object[] {7, "engine/actor_default", 200.0, 300.0, "myPlayer", 0}; 
    
    @Test
    public void gameFactoryTest() throws FactoryException{
        Game myGame = new Game();
        GameEngine myEngine = new GameEngine();        
        GameFactory factory = new GameFactory(myEngine);
        GameObject myObject;
        try {
            factory.processOrder(myGame, 1, 1, CREATE_PLAYER_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
        
        assertEquals(myObject, "");
    }
}

/*// for reference only:
 * 
 * @Test(expected = FactoryException.class)
public void testNonexistentClassThrowsFactoryException () throws Exception {
    GameFactory factory = new GameFactory(JGEngine);
    factory.create("nonexistent");
    
    assertEquals(300, Double.parseDouble(model.getVariable(":x")), .0001);
}*/
