package engineTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import stage.Game;
import controller.DataController;
import engine.GameEngine;

public class AttributeTest {
	public static final String CREATE_ACTOR_ORDER = "CreateActor,ID,1,Image,actor_default.png,20,20,Position,0.0,0.0,Name,Hero,CollisionID,0,Lives,1";
	public static final String MODIFY_ACTOR_SHOOT = "ModifyActor,ID,1,SlowShoot,SlowShoot,actor_default.png,10,10,2,5,4";
	public static final String CREATE_LEVEL_1 = "CreateLevel,ID,1";
	public static final String CREATE_SCENE_1 = "CreateScene,ID,1,ID,0";
	public static final String SWITCH_SCENE_1 = "SwitchScene,ID,1,ID,0";
	public static final String DEFAULT_GRAVITY = "ModifyGravity,Magnitude,0.0";
	
	
	protected DataController myController;
	protected GameEngine myEngine;
	protected Game myGame;

	protected void setUp(){
    	myController = new DataController();
    	myEngine = myController.initGameEngine(true);
    	myGame = myEngine.getGame();
	}
	
	@Test
	public void testReceiveOrder(){
    	myController = new DataController();
    	myEngine = myController.initGameEngine(true);
    	myGame = myEngine.getGame();
    	
    	myController.receiveOrder(CREATE_LEVEL_1);
    	myController.receiveOrder(CREATE_SCENE_1);
    	myController.receiveOrder(SWITCH_SCENE_1);
    	myController.receiveOrder(CREATE_ACTOR_ORDER);
    	myController.receiveOrder(MODIFY_ACTOR_SHOOT);
    	List<String> att = myGame.getAttributes();
    	assertEquals(att.get(0), DEFAULT_GRAVITY);
    	assertEquals(att.get(1), CREATE_LEVEL_1);
    	assertEquals(att.get(2), "ModifyLevel,ID,1,SetInitialScene,0");
    	assertEquals(att.get(3), CREATE_SCENE_1);
    	assertEquals(att.get(4), SWITCH_SCENE_1);
    	assertEquals(att.get(5), CREATE_ACTOR_ORDER);
    	assertEquals(att.get(6), MODIFY_ACTOR_SHOOT);
	}
	
	
}
