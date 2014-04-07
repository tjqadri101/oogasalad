package controller;

import java.util.List;
import java.util.ResourceBundle;

import engine.GameEngine;
import gameFactory.GameFactory;
import parser.ParseGame;
import stage.Game;
import reflection.Reflection;

public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_CREATEORMODIFY = "CreationOrModify";
	public static final String IS_CREATION = "Creation";
	
	protected Game myGame;
    protected int currentLevelID;
    protected int currentSceneID;
	protected GameFactory myFactory;
	protected ParseGame myParser;
	protected GameEngine myGameEngine;
	protected ResourceBundle myCreateModifyTeller;
	
	public DataController(){
		myParser = new ParseGame();
		myGame = new Game();
		myGameEngine = new GameEngine();
		myFactory = new GameFactory(myGameEngine);
		currentLevelID = 0;
		currentSceneID = 0;
		myCreateModifyTeller = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_CREATEORMODIFY);
	}
	
	/*
	 * Called by Game Authorizing Environment to send the command String
	 * Input is a String order
	 */
	public void receiveOrder(String order){
		String[] orders = order.split(",");
		String methodName = myCreateModifyTeller.getString(orders[0]);
		Reflection.callMethod(this, methodName, order);	
	}
	
	/*
	 * Called by Game Authorizing Environment to export the game data
	 * Input is a url to the XML file created by the GAE
	 */
	public void exportXML(String url){
		myParser.writeToFile(myGame, url);
	}
	
	/*
	 * Called by PlayView to import the game data
	 * Input is a url to the XML file loaded by PlayView
	 */
	public void readXML(String url){
		List<String> orders = myParser.readFromFile(url);
		for(String order: orders){
			receiveOrder(order);
		}
	}
	
	
	protected void callFactoryToProcess(String order){
		myFactory.processOrder(myGame, currentLevelID, currentSceneID, order);
	}
	
	protected void switchToScene(String order){
		String[] orders = order.split(",");
		currentSceneID = Integer.parseInt(orders[2]);
	}
	
	protected void switchToLevel(String order){
		String[] orders = order.split(",");
		currentLevelID = Integer.parseInt(orders[2]);
	}
}
