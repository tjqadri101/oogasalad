package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import engine.GameEngine;
import gameFactory.GameFactory;
import objects.GameObject;
import stage.Game;
import stage.Level;
import stage.Scene;
import reflection.Reflection;

public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_CREATEORMODIFY = "CreationOrModify";
	public static final String IS_CREATION = "Creation";
	
	protected Game myGame;
    protected Level currentLevel;
    protected Scene currentScene;
	//Exporter myExporter;
	protected GameFactory myFactory;
	//Importer myImporter;
	//protected GameEngine myGameEngine;
	protected ResourceBundle myCreateModifyTeller;
	protected Scene myCurrentScene;
	
//  protected Map<Integer, Level> myLevels = new HashMap<Integer, Level>();
//  protected Map<Integer, Scene> myScenes = new HashMap<Integer, Scene>();
//  protected Map<Integer, GameObject> myGameObjects = new HashMap<Integer, GameObject>();
	
	public DataController(){
		//myExporter = new Exporter();
		myFactory = new GameFactory();
		//myImporter = new Importer();
		myGame = new Game();
		myCreateModifyTeller = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_CREATEORMODIFY);
	}
	
	public void receiveOrder(String order){
		String[] orders = order.split(",");
		String methodName = myCreateModifyTeller.getString(orders[0]);
		Reflection.callMethod(this, methodName, order);	
	}
	
	public void receiveXML(String url){
		//Game game = myImporter.parser(url);
	}
	
	protected void createGameObject(String order){
		Object o = myFactory.processOrder(order);
		GameObject created = (GameObject)o;
		myCurrentScene.addObject(created);
	}
	
	protected void createLevel(String order){
		Object o = myFactory.processOrder(order);
		Level created = (Level)o;
	}
	
	protected void createScene(String order){
		Object o = myFactory.processOrder(order);
		Scene created = (Scene)o;
	}
	
	protected void modifyGameObject(String order){
		
	}
	
	protected void modifyLevel(String order){
		
	}
	
	protected void modifyScene(String order){
		
	}
}
