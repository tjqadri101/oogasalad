package controller;
import saladConstants.SaladConstants;

public class GAEController {
	
	DataController dcontroller;
	
	public GAEController(){
		//dcontroller = new DataController();
	}
	
	public void createPlayer(int ID,String url,String name){
		String order = SaladConstants.CREATE_PLAYER + ",ID,"+ID+",Image,"+url+",Position,0,0,Name,"+name;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
	}
 	
	public void createActor(int ID,String url,String name){
		String order = SaladConstants.CREATE_ACTOR + ",ID,"+ID+",Image,"+url+",Position,0,0,Name,"+name;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
		
	}
	
	public void deleteActor(int ID){
		String order = "DeleteActor,ID,"+ID;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
		
	}
	
	
	public void modifyActorPosition(int ID, double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + ",ID,"+ID+",Position," + xPos + "," + yPos;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + ",ID,"+ID+",Position," + xSpeed + "," + ySpeed;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
	}
	public void createLevel(int ID){
		String order = SaladConstants.CREATE_LEVEL + ",ID,"+ID;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyLevel(int ID, String function){
		String order = SaladConstants.MODIFY_LEVEL + ",ID,"+ID+ function;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
	}
	
	public void switchLevel(int ID){
		String order = SaladConstants.SWITCH_LEVEL + ",ID,"+ID;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void createScene(int ID, String path){
		String order = SaladConstants.CREATE_SCENE + ",ID,"+ID+",Image,"+path;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyScene(int ID, String function){
		String order = SaladConstants.MODIFY_SCENE + ",ID,"+ID+ function;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
		
	}
	
	public void switchScene(int ID){
		String order = SaladConstants.SWITCH_SCENE + ",ID,"+ID;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
	}
	
}
