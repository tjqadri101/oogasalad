import controller.DataController;
import controller.GAEController;


public class engineMain {
	
    public static void main (String[] args)

    {	
    	DataController controller = new DataController();
    	controller.receiveOrder("CreateLevel,ID,1");
    	controller.receiveOrder("CreateScene,ID,1,ID,0");
    	controller.receiveOrder("SwitchScene,ID,1,ID,0");
    }
    
}
