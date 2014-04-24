package engineTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import stage.Game;
import controller.DataController;
import engine.GameEngine;

public class DataControllerTests {
	public static final String CREATE_ACTOR_ORDER = "CreateActor,ID,0,PlayerImage,actor_default.png,20,20,Position,0.0,0.0,Name,Hero,CollisionID,0,Lives,1";
	
	protected DataController myDataController;
	protected GameEngine myEngine;
	protected Game myGame;

	protected void setUp(){
    	myDataController = new DataController();
    	myEngine = myDataController.initGameEngine(true);
//    	myGame = myEngine.getGame();
		myGame.addLevel(1);
		myGame.addScene(1, 0);
		myEngine.setCurrentScene(1, 0); 
	}
    
	@Test
	public void testReceiveOrder(){
//		String order = CREATE_PLAYER_ORDER;
//		myDataController.receiveOrder(order);
	}
	
	@Test
	public void testConvertStringToObjects(){ 
    	myDataController = new DataController();
    	myEngine = myDataController.initGameEngine(true);
    	myGame = myEngine.getGame();
		myGame.addLevel(1);
		myGame.addScene(1, 0);
		myEngine.setCurrentScene(1, 0); 
    	
		String order = CREATE_ACTOR_ORDER;
//		List<Object> tests = myDataController.convertOrderToObjects(order);
//		for(Object o: tests){
//			System.out.println(o +"  ///Object Type: " +o.getClass());
//		}
		
		List<Object> objects = new ArrayList<Object>();
		objects.add("CreateActor");
		objects.add("ID");
		objects.add((Integer)0);
		objects.add("PlayerImage");
		objects.add("actor_default.png");
		objects.add((Integer) 20);
		objects.add((Integer) 20);
		objects.add("Position");
		objects.add((Double)0.0);
		objects.add((Double)0.0);
		objects.add("Name");
		objects.add("Hero");
		objects.add((String)"CollisionID");
		objects.add((Integer)0);
		objects.add("Lives");
		objects.add((Integer)1);
		
//		assertEquals(objects, tests);
	}

}
