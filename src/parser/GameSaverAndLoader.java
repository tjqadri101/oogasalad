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
	private ReadXML myReader; 
	private WriteXML myWriter; 

	
	public GameSaverAndLoader(){ 
		myReader = new ReadXML(); 
		myWriter = new WriteXML(); 
	}

	/*
	 * @param game A game object 
	 * @param url String referencing location to store the game object
	 * @return Nothing
	 */
	public void save(List<String> attributes, String url) throws IOException { 
			myWriter.write(attributes, url); 
	}
	
	/*
	 *  
	 * @param url String referencing location to store the game object
	 * @return Game A game object
	 */
	public List<String> load(String url) throws Exception{
		return myReader.read(url); 
	}
	

}
