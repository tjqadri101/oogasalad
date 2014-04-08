package engineTests;

import org.junit.Test;
import controller.DataController;

public class DataControllerTests {
	protected DataController myDataController;

    protected void setUp(){
		myDataController = new DataController();
    }
    
	@Test
	public void testReceiveOrder(){
		String order = "CreatePlayer,ID,0,Image,hero.gif,Position,5,5,Name,Hero";
		myDataController.receiveOrder(order);
	}
}
