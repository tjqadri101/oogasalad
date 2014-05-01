import stage.Game;
import controller.DataController;
import controller.GAEController;
import engine.GameEngine;


public class engineMain {
	
    public static void main (String[] args)

    {	
    	DataController controller = new DataController();
    	GameEngine engine = controller.initGameEngine(true);
    	Game game = engine.getGame();
    	
    	controller.receiveOrder("CreateLevel,ID,1");
    	controller.receiveOrder("CreateScene,ID,1,ID,0");
    	controller.receiveOrder("SwitchScene,ID,1,ID,0");
    }
    
}
