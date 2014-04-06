package controller;

import gameFactory.GameFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import jgame.JGObject;
import objects.GameObject;
import stage.Game;
import stage.Level;
import stage.Scene;

public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_CREATEORMODIFY = "CreationOrModify";
	public static final String IS_CREATION = "Creation";
	
    protected Map<String, Level> levelSet = new HashMap<String, Level>();
    protected Map<String, Scene> sceneSet = new HashMap<String, Scene>();
    protected Map<String, JGObject> objectSet = new HashMap<String, JGObject>();
    // Note to David: to set Map of those in respective level, scene 
    // this implementation requires GAE editing to be scene specific 
    
    protected Level currentLevel;
    protected Scene currentScene;
	//Exporter myExporter;
	protected GameFactory myFactory;
	//Importer myImporter;
	//GameEngine myGameEngine;
	protected Game myGame;
	protected ResourceBundle myCreateModifyTeller;
	protected Scene myCurrentScene;
	
	public DataController(){
		//myExporter = new Exporter();
		myFactory = new GameFactory();
		//myImporter = new Importer();
		myGame = new Game();
		myCreateModifyTeller = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_CREATEORMODIFY);
	}
	
	public void receiveOrder(String order){
		String[] orders = order.split(",");
		if(myCreateModifyTeller.getString(orders[0]) == IS_CREATION){
			GameObject o = myFactory.processOrder(order);
			myCurrentScene.addObject(o);
		}
		else if (currentScene.getObjects().containsKey(orders[1])){
			
		}
		else {
			System.out.println("Error"); // should never reach here
		}
		//Object o = myFactory.processOrder(order);
		//myGame.add(o);
		//myGameEngine.receiveObject(o);
	}
	
	public void receiveXML(String url){
		//Game game = myImporter.parser(url);
	}

}
