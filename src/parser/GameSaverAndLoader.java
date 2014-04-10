package parser;
/*
 * @author Anthony Olawo 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import engine.GameEngine;
import objects.GameObject;
import objects.Player;
import jgame.JGColor;
import stage.Game;
import stage.Level;
import stage.Scene;


public class GameSaverAndLoader { 
	private XMLReader myXMLReader; 
	private XMLWriter myXMLWriter; 
	private Serializer mySerializer; 
	
	public GameSaverAndLoader(){ 
		myXMLReader = new XMLReader(); 
		myXMLWriter = new XMLWriter(); 
	}

	/*
	 * @param game A game object 
	 * @param url String referencing location to store the game object
	 * @return Nothing
	 */
	public void save(List<String> attributes, String url) throws IOException { 
			myXMLWriter.write(attributes, url); 
	}
	
	public void save(Object obj, String url){
		mySerializer.serialize(obj, url); 
	}
	/*
	 *  
	 * @param url String referencing location to store the game object
	 * @return Game A game object
	 */
	public List<String> load(String url) throws Exception{
		return myXMLReader.read(url); 
	}
	
	public static void main( String [] args){
		GameEngine myEngine = new GameEngine();
		myEngine.setGame(new Game());
		Game myGame = myEngine.getGame();
		myGame.addLevel(0);
		myGame.addScene(0, 0); 
		myEngine.setCurrentLevel(0);

		myEngine.setCurrentScene(0);
		myEngine.createActor(0, "actor_default.png", 0.0, 0.0, "Justin", 0);
		
		
		GameSaverAndLoader a = new GameSaverAndLoader (); 
		a.save(myGame, "Game"); 
		Game test = new Game();
		
		
		/*try {
			test = a.load("Game");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}

}
