package controller;
import saladConstants.SaladConstants;
import stage.Game;

public class GAEController {
	
	private DataController myDataController;
	
	public GAEController(){
		myDataController = new DataController();
		myDataController.initGameEditing(new Game());
	}
	
	public void createPlayer(int ID,String url,String name){
		String order = SaladConstants.CREATE_PLAYER + ",ID,"+ID+",Image,"+url+",Position,0,0,Name,"+name;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
 	
	public void createActor(int ID,String url,String name){
		String order = SaladConstants.CREATE_ACTOR + ",ID,"+ID+",Image,"+url+",Position,0,0,Name,"+name;
		//myDataController.receiveOrder(order);
		System.out.println(order);
		
	}
	
	public void deleteActor(int ID){
		String order = "DeleteActor,ID,"+ID;
		//myDataController.receiveOrder(order);
		System.out.println(order);
		
	}
	
	
	public void modifyActorPosition(int ID, double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + ",ID,"+ID+",Position," + xPos + "," + yPos;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + ",ID,"+ID+",Position," + xSpeed + "," + ySpeed;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	public void createLevel(int ID){
		String order = SaladConstants.CREATE_LEVEL + ",ID,"+ID;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyLevel(int ID, String function){
		String order = SaladConstants.MODIFY_LEVEL + ",ID,"+ID+ function;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void switchLevel(int ID){
		String order = SaladConstants.SWITCH_LEVEL + ",ID,"+ID;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void createScene(int ID, String path){
		String order = SaladConstants.CREATE_SCENE + ",ID,"+ID+",Image,"+path;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyScene(int ID, String function){
		String order = SaladConstants.MODIFY_SCENE + ",ID,"+ID+ function;
		//myDataController.receiveOrder(order);
		System.out.println(order);
		
	}
	
	public void switchScene(int ID){
		String order = SaladConstants.SWITCH_SCENE + ",ID,"+ID;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public DataController getDataController(){
		return myDataController;
	}
}
