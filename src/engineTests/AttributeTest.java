package engineTests;

import static org.junit.Assert.*;

import org.junit.Test;

import stage.Game;
import controller.DataController;
import engine.GameEngine;

public class AttributeTest {
	public static final String CREATE_ACTOR_ORDER = "CreateActor,ID,0,PlayerImage,actor_default.png,20,20,Position,0.0,0.0,Name,Hero,CollisionID,0,Lives,1";
	
	protected DataController myDataController;
	protected GameEngine myEngine;
	protected Game myGame;

	protected void setUp(){
    	myDataController = new DataController();
    	myEngine = myDataController.initGameEngine(true);
    	myGame = myEngine.getGame();
		myGame.addLevel(1);
		myGame.addScene(1, 0);
		myEngine.setCurrentScene(1, 0); 
	}
	
	@Test
	public void testReceiveOrder(){
//		String order = CREATE_PLAYER_ORDER;
//		myDataController.receiveOrder(order);
	}
}
