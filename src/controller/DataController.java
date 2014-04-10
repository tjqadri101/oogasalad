package controller;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.parsers.ParserConfigurationException;

import engine.GameEngine;
import gameFactory.GameFactory;
import parser.GameSaverAndLoader;
import stage.Game;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_CREATEORMODIFY = "KeyDataController";
	
	protected Game myGame;
	protected GameFactory myFactory;
	protected GameSaverAndLoader myGameSaverAndLoader;
	protected GameEngine myGameEngine;
	protected ResourceBundle myOrderReflector;
	
	public DataController(){
		myGameSaverAndLoader = new GameSaverAndLoader(); 
		myOrderReflector = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_CREATEORMODIFY);
	}
	
	/**
	 * Called by Game Authorizing Environment to display the engine inside its GUI
     * @return Game Engine
	 */
	public GameEngine initGameEngine(){
		myGame = new Game();
		myGameEngine = new GameEngine();
		myGameEngine.setGame(myGame);
		myFactory = new GameFactory(myGameEngine);
		return myGameEngine;
	}
	
	
	/**
	 * Called by Game Authorizing Environment to send the command String
	 * @param a list of objects: order
     * @return nothing
	 */
	public void receiveOrder(String order){
		callFactoryToProcess(order);
	}
	
	
	/**
	 * Called by Game Authorizing Environment to export the game data
	 * @param a String url to the XML file created by the GAE
	 * @return nothing
	 */
	public void exportXML(String url) throws ParserConfigurationException, IOException{
		myGameSaverAndLoader.save(myGame.getAttributes(), url);
	}
	
	
	/**
	 * Called by PlayView to import the game data
	 * @param a String url to the XML file loaded by PlayView
	 * @return nothing
	 */
	public void readXML(String url) throws Exception {
		List<String> orders = myGameSaverAndLoader.load(url);
		for(String order: orders){
			callFactoryToProcess(order);
		}
	}
	
	
	/**
	 * Called by Game Authorizing Environment to read the info about a specific Game Object (i.e. Actor)
	 * @param an id number matched to the Game Object to get
	 * @return a list of String orders attached to the Game Object
	 */
	public List<String> getObjectInfo(int id){
		return myGame.getGameObject(myGameEngine.getCurrentLevelID(), myGameEngine.getCurrentSceneID(), id).getAttributes();
	}
	
	/**
	 * Called by Game Authorizing Environment to retrieve the current level ID
	 * @return the current level ID
	 */
	public int getCurrentLevelID(){
		return myGameEngine.getCurrentLevelID();
	}
	
	/**
	 * Called by Game Authorizing Environment to retrieve the current scene ID
	 * @return the current scene ID
	 */
	public int getCurrentSceneID(){
		return myGameEngine.getCurrentSceneID();
	}
	
	
	/**
	 * Do not call this method directly
	 * This method is called within DataController by Reflection
	 */
	public void callFactoryToProcess(String order) {
		try{
//			myFactory.processOrder(order);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
	}
}
