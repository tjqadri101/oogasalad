package controller;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import engine.GameEngine;
import gameFactory.GameFactory;
import parser.GameSaverAndLoader;
import stage.Game;
import reflection.Reflection;
/*
 * @Author: Justin (Zihao) Zhang
 */
public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_CREATEORMODIFY = "KeyDataController";
	
	protected Game myGame;
    protected int currentLevelID;
    protected int currentSceneID;
	protected GameFactory myFactory;
	protected GameSaverAndLoader myGameSaverAndLoader;
	protected GameEngine myGameEngine;
	protected ResourceBundle myOrderReflector;
	
	public DataController(){
		myGameSaverAndLoader = new GameSaverAndLoader(); 
		myOrderReflector = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_CREATEORMODIFY);
	}
	
	/*
	 * Called by Game Authorizing Environment to display the engine inside its GUI
	 */
	public GameEngine initGameEngine(Game game){
		myGame = game;
		myGameEngine = new GameEngine();
		myGameEngine.setGame(myGame);
		myFactory = new GameFactory(myGameEngine);
		return myGameEngine;
	}
	
	
	/*
	 * Called by Game Authorizing Environment to send the command String
	 * Input is a String order
	 */
	public void receiveOrder(String order){
		String[] orders = order.split(",");
		String methodName = myOrderReflector.getString(orders[0]);
		Reflection.callMethod(this, methodName, order);	
	}
	
	
	/*
	 * Called by Game Authorizing Environment to export the game data
	 * Input is a url to the XML file created by the GAE
	 */
	public void exportXML(String url) throws ParserConfigurationException, IOException{
		myGameSaverAndLoader.save(myGame, url);
	}
	
	
	/*
	 * Called by PlayView to import the game data
	 * Input is a url to the XML file loaded by PlayView
	 */
	public Game readXML(String url) throws Exception {
		return myGameSaverAndLoader.load(url);
	}
	
	
	/*
	 * Called by Game Authorizing Environment to read the info about a specific Game Object (i.e. Actor)
	 * Input is an id number matched to the Game Object
	 */
	public List<String> getObjectInfo(int id){
		return myGame.getGameObject(currentLevelID, currentSceneID, id).getAttributes();
	}
	
	public int getCurrentLevelID(){
		return currentLevelID;
	}
	
	public int getCurrentSceneID(){
		return currentSceneID;
	}
	
	
	/*
	 * Do not call this method directly
	 * This method is called within DataController by Reflection
	 */
	public void callFactoryToProcess(String order) {
		try{
//			myFactory.processOrder(myEngine, myGame, currentLevelID, currentSceneID, order);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
	}
	
	/*
	 * Do not call this method directly
	 * This method is called within DataController by Reflection
	 */
	public void switchToScene(String order){
		String[] orders = order.split(",");
		currentSceneID = Integer.parseInt(orders[2]);
		myGameEngine.setCurrentScene(currentLevelID, currentSceneID);
	}
	
	/*
	 * Do not call this method directly
	 * This method is called within DataController by Reflection
	 */
	public void switchToLevel(String order){
		String[] orders = order.split(",");
		currentLevelID = Integer.parseInt(orders[2]);
	}
}
