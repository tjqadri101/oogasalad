package controller;

import imagebuffer.ImageBuffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import engine.GameEngine;
import gameFactory.GameFactory;
import parser.GameSaverAndLoader;
import reflection.Reflection;
import saladConstants.SaladConstants;
import stage.Game;
import util.SaladUtil;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_DATA_FORMAT = "TypeFormat";
	public static final String DEFAULT_REFLECTION_METHODS = "DataFormatReflection";
	
	protected Game myGame;
	protected GameFactory myFactory;
	protected GameSaverAndLoader myGameSaverAndLoader;
	protected GameEngine myGameEngine;
	protected ResourceBundle myDataFormat;
	protected ResourceBundle myReflectionMethods;
	protected ImageBuffer myImageBuffer;
	
	public DataController(){
		myGameSaverAndLoader = new GameSaverAndLoader(); 
		myDataFormat = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_DATA_FORMAT);
		myReflectionMethods = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_REFLECTION_METHODS);
		myImageBuffer = new ImageBuffer();
	}
	
	/**
	 * Called by Game Authorizing Environment to display the engine inside its GUI
	 * @param A boolean indicating whether the engine is in editing mode or playing mode
     * @return Game Engine
	 */
	public GameEngine initGameEngine(boolean isEditing){
		myGame = new Game();
		myGameEngine = new GameEngine(isEditing);
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
		System.out.println("DataController: " + "received order " + order);
		callFactoryToProcess(convertOrderToObjects(order));
	}
	
	/**
	 * Do not call this method directly; called within DataController
	 * Called to convert String order to a list of Objects in their original data format (i.e. Integer)
	 */
	public List<Object> convertOrderToObjects(String order){
		List<Object> answer = new ArrayList<Object>();
		String[] orders = order.split(",");
		int i = 0;
		answer.add(orders[i]); //add key
		i ++;
		while(i < orders.length){
			answer.add(orders[i]); //add type
			String type = myDataFormat.getString(orders[i]);
			String[] types = type.split(","); 
			if(!types[0].equals(SaladConstants.NULL_TOKEN)){
				i = i + 1;
				for(int j = 0; j < types.length; j ++){
					answer.add(Reflection.callMethod(this, myReflectionMethods.getString(types[j]), orders[i+j]));
				}
			}
			i = i + types.length;
		}
		return answer;
	}
	
	/**
	 * Do not call this method directly; called by Reflection within DataController
	 */
	public Integer convertStringToInteger(String s){
		return Integer.valueOf(s);
	}
	
	/**
	 * Do not call this method directly; called by Reflection within DataController
	 */
	public Double convertStringToDouble(String s){
		return Double.valueOf(s);
	}
	
	/**
	 * Do not call this method directly; called by Reflection within DataController
	 */
	public String convertStringToString(String s){
		return s;
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
		SaladUtil.printStringList(orders);
		for(String order: orders){
			callFactoryToProcess(convertOrderToObjects(order));
		}
	}
	
	
	/**
	 * Called by Game Authorizing Environment to read the info about an Actor
	 * @param an id number matched to the Game Object to get
	 * @return a list of String orders attached to the Game Object
	 */
	public List<String> getActorInfo(int id){
		return myGame.getNonPlayer(myGameEngine.getCurrentLevelID(), myGameEngine.getCurrentSceneID(), id).getAttributes();
	}
	
	/**
	 * Called by Game Authorizing Environment to read the info about the Player
	 * @return a list of String orders attached to Player
	 */
	public List<String> getPlayerInfo(){
		return myGame.getPlayer(Game.NONUSE_ID, Game.NONUSE_ID, Game.NONUSE_ID).getAttributes();
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
	 * Do not call this method directly; called within DataController
	 * Called to enable factory to process the order
	 */
	protected void callFactoryToProcess(List<Object> order) {
		try{
			myFactory.processOrder(order);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
	}
	
	/**
	 * Called by GAE to save and resize the image
	 * @param x
	 * @param y
	 * @param source
	 * @throws IOException
	 */
	public void uploadImage(int x, int y, String source) throws IOException {
		myImageBuffer.resizedUpload(x, y, source);
	}
}
