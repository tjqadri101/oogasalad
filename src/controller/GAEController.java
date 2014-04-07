package controller;

public class GAEController {
	
	DataController dcontroller;
	
	public GAEController(){
		//dcontroller = new DataController();
	}
	
	public void createPlayer(){
		
	}
 	
	public void createActor(int ID,String url,String name){
		String order = "CreateActor,ID,"+ID+",Image,"+url+",Position,0,0,Name,"+name;
		//dcontroller.receiveOrder(order);
		System.out.println(order);
		
	}
	
	
	public void modifyActor(){
		
	}
	
	public void createLevel(){
		
	}
	
	public void modifyLevel(){
		
	}
	
	public void switchLevel(){
		
	}
	
	
	public void createScene(){
		
	}
	
	public void modifyScene(){
		
	}
	
	public void switchScene(){
		
	}
	
}
