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
			for(String s: attributes){ 
				System.out.println("Attribute string: " + s); 
			}
			myXMLWriter.write(attributes, url); 
	}
	
	/*
	 *  
	 * @param url String referencing location to store the game object
	 * @return Game A game object
	 */
	public List<String> load(String url) throws Exception{
		return myXMLReader.read(url); 
	}

}
