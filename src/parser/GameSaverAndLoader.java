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

import objects.GameObject;
import jgame.JGColor;
import stage.Game;
import stage.Level;
import stage.Scene;

//import com.google.gson.Gson; 
//import com.google.gson.GsonBuilder;

public class GameSaverAndLoader { 
	private Parser myParser; 
	
	public GameSaverAndLoader(){ 
		myParser = new Parser(); 
	}

	public void save(Game game, String url) throws IOException { 
			myParser.write(game, url);
	}
	
	public Game load(String url) throws Exception{
		return myParser.read(url); 
	}
	

}
