package engineTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import stage.Game;
import controller.DataController;
import engine.GameEngine;

public class DataControllerTests {
	public static final String CREATE_ACTOR_ORDER = "CreateActor,ID,0,Image,actor_default.png,Position,0,0,Name,Hero,CollisionID,0";
	
	protected DataController myDataController;
	protected GameEngine myEngine;
	protected Game myGame;

	protected void setUp(){
    	myDataController = new DataController();
    	myEngine = myDataController.initGameEngine();
//		myGame.addLevel(1);
//		myGame.addScene(1, 0);
//		myEngine.setCurrentScene(0);
//		myEngine.setCurrentLevel(0);    
	}
    
	@Test
	public void testReceiveOrder(){
//		String order = CREATE_PLAYER_ORDER;
//		myDataController.receiveOrder(order);
	}
	
	@Test
	public void testConvertStringToObjects(){
    	myDataController = new DataController();
    	myEngine = myDataController.initGameEngine();
    	
		String order = CREATE_ACTOR_ORDER;
		List<Object> tests = myDataController.convertOrderToObjects(order);
		for(Object o: tests){
			System.out.println(o +"  ///Object Type: " +o.getClass());
		}
		
		List<Object> objects = new ArrayList<Object>();
		objects.add("CreateActor");
		objects.add("ID");
		objects.add((Integer)0);
		objects.add("Image");
		objects.add("actor_default.png");
		objects.add("Position");
		objects.add((Double)0.0);
		objects.add((Double)0.0);
		objects.add("Name");
		objects.add("Hero");
		objects.add((String)"CollisionID");
		objects.add((Integer)0);
		
		assertEquals(objects, tests);
	}

}
