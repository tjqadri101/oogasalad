package controller;

import imagebuffer.ImageBuffer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import objects.NonPlayer;
import engine.GameEngine;
import gameFactory.GameFactory;
import parser.GameSaverAndLoader;
import stage.Game;
import util.SaladUtil;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public class DataController {
	
	protected Game myGame;
	protected GameFactory myFactory;
	protected GameSaverAndLoader myGameSaverAndLoader;
	protected GameEngine myGameEngine;
	protected ImageBuffer myImageBuffer;
	
	public DataController(){
		myGameSaverAndLoader = new GameSaverAndLoader(); 
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
		System.out.println("**DataController received order: " + order);
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
	 * Called by Game Authorizing Environment to get the Game for various manipulations
	 * @return Game
	 */
	public Game getGame(){
		return myGame;
	}
	
	/**
	 * Called by PlayView to import the game data
	 * @param a String url to the XML file loaded by PlayView
	 * @return nothing
	 */
	public void readXML(String url) throws Exception {
		List<String> orders = myGameSaverAndLoader.load(url);
		System.out.println("****DataController readXML****"); // delete
		SaladUtil.printStringList(orders); // delete
		for(String order: orders){ 
			System.out.println("**ReadXML received order: " + order);
			callFactoryToProcess(order); 
		}
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
	 * Called by Game Authorizing Environment to retrieve the current clicked ID
	 * @return selected ID
	 */
	public int getSelectedID(){
		return myGameEngine.getClickedID();
	}
	
	
	/**
	 * Do not call this method directly; called within DataController
	 * Called to enable factory to process the order
	 */
	protected void callFactoryToProcess(String order) {
		try{
			myFactory.processOrder(order);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
	}
	
	/**
	 * Called by Game Authorizing Environment to save and resize the image
	 * @param x
	 * @param y
	 * @param source
	 * @throws IOException
	 */
	public void uploadImage(int x, int y, String source) throws IOException {
		myImageBuffer.resizedUpload(x, y, source);
	}
	
	/**
	 * Called by Game Authorizing Environment to revive the last killed/dead object
	 */
	public void reviveObject() {
		myGameEngine.reviveObject();
	}
	
	/**
	 * Called by Game Authorizing Environment to get a map of non-players
	 * @param sceneID
	 * @return a map mapping from uniqueIDs to non-players
	 */
	public Map<Integer, NonPlayer> getMapOfNonPlayers(int sceneID){
		Map<Integer, NonPlayer> nonPlayerMap = myGame.getLevel(getCurrentLevelID()).getScene(sceneID).getObjectMap();
		return nonPlayerMap;
	}
	
	/**
	 * Called by Game Authorizing Enviornment to get a non-player matched with the uniqueID
	 * @param objectID
	 * @return non-player
	 */
    public NonPlayer getNonPlayer(int objectID){
        return myGame.getLevel(getCurrentLevelID()).getNonPlayer(getCurrentSceneID(), objectID);
    }
}
