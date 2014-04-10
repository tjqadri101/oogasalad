package engineTests;

import static org.junit.Assert.*;

import org.junit.Test;

import stage.Game;
import controller.DataController;

public class DataControllerTests {
	protected DataController myDataController;

    protected void setUp(){
		myDataController = new DataController();
//		myDataController.initGameEngine(new Game());
    }
    
	@Test
	public void testReceiveOrder(){
//		String order = "CreatePlayer,ID,0,Image,hero.gif,Position,5,5,Name,Hero";
//		myDataController.receiveOrder(order);
	}
	
	@Test
	public void testSwitchLevel(){
		String order = "SwitchLevel,ID,1";
		myDataController = new DataController();
//		myDataController.receiveOrder(order);
		assertEquals(1, myDataController.getCurrentLevelID());
	}
	
	@Test
	public void testSwitchScene(){
		String order = "SwitchScene,ID,1";
		myDataController = new DataController();
//		myDataController.receiveOrder(order);
		assertEquals(1, myDataController.getCurrentSceneID());
	}
}
