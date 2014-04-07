package controller;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import engine.GameEngine;
import gameFactory.GameFactory;
import parser.ParseGame;
import stage.Game;
import reflection.Reflection;
/*
 * @Author: Justin (Zihao) Zhang
 */
public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_CREATEORMODIFY = "CreationOrModify";
	
	protected Game myGame;
    protected int currentLevelID;
    protected int currentSceneID;
	protected GameFactory myFactory;
	protected ParseGame myParser;
	protected GameEngine myGameEngine;
	protected ResourceBundle myOrderReflector;
	
	public DataController(){
		myParser = new ParseGame();
		myGame = new Game();
		myGameEngine = new GameEngine();
		myFactory = new GameFactory(myGameEngine);
		myOrderReflector = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_CREATEORMODIFY);
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
	public void exportXML(String url) throws ParserConfigurationException{
		myParser.writeToFile(myGame, url);
	}
	
	/*
	 * Called by PlayView to import the game data
	 * Input is a url to the XML file loaded by PlayView
	 */
	public void readXML(String url) throws ParserConfigurationException, SAXException, IOException{
		List<String> orders = myParser.readFromFile(url);
		for(String order: orders){
			receiveOrder(order);
		}
	}
	
	protected void callFactoryToProcess(String order) {
		try{
			myFactory.processOrder(myGame, currentLevelID, currentSceneID, order);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
	}
	
	protected void switchToScene(String order){
		String[] orders = order.split(",");
		currentSceneID = Integer.parseInt(orders[2]);
		myGameEngine.setCurrentScene(currentLevelID, currentSceneID);
	}
	
	protected void switchToLevel(String order){
		String[] orders = order.split(",");
		currentLevelID = Integer.parseInt(orders[2]);
	}
}
