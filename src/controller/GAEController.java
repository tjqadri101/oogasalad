package controller;

import game_authoring_environment.FullView;
import game_authoring_environment.GAE;
import game_authoring_environment.MenuBar;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JFrame;

import engine.GameEngine;
import saladConstants.SaladConstants;
import stage.Game;

public class GAEController {
	
	private DataController myDataController;
	private GAE g;
	public static final String TITLE = "OOGASalad iTeam";
	private static FullView fv;
	private static MenuBar mb; 
	private static GAEController gController;
	private HashMap<String, Image> availableImages;
	private GameEngine myGameEngine;
	
	public GAEController(){
		myDataController = new DataController();
		myGameEngine = myDataController.initGameEngine();
		createGAE(this);
		//g = new GAE(this);
	}
	
	public void createGAE(GAEController gController){
		JFrame mainFrame = new JFrame(TITLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fv = new FullView(gController);
		mb = new MenuBar(gController);
		mainFrame.add(fv, BorderLayout.CENTER);
		mainFrame.add(mb, BorderLayout.NORTH);
		mainFrame.pack();
		mainFrame.setVisible(true);
		System.out.println("help");
	}
	
	
	public GameEngine getEngine(){
		return myGameEngine;
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
	
	public void switchActiveTab(int index){
		
	}
	
}
