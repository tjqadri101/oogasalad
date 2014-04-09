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
	private Parser myParser; 
	
	
	public GameSaverAndLoader(){ 
		myParser = new Parser(); 
	}

	/*
	 * @param game A game object 
	 * @param url String referencing location to store the game object
	 * @return Nothing
	 */
	public void save(Game game, String url) throws IOException { 
			myParser.write(game, url);
	}
	
	/*
	 *  
	 * @param url String referencing location to store the game object
	 * @return Game A game object
	 */
	public Game load(String url) throws Exception{
		return myParser.read(url); 
	}
	
	
	public static void main (String [] args) {
		GameEngine myEngine = new GameEngine();
		myEngine.setGame(new Game());
		Game myGame = myEngine.getGame();
		myEngine.createActor("0", null, 0, 0, "Justin", 0, 0, 0);
		
		myGame.addLevel(0);
		myGame.addScene(0, 0); 
		myGame.addObject(0, 0, myPlayer);  
		
		GameSaverAndLoader a = new GameSaverAndLoader (); 
		try {
			a.save(myGame, "Game");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Game test = new Game();
		try {
			test = a.load("Game");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(test.getGameObject(0,0,0).getAttributes());
	}

}
