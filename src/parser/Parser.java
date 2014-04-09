package parser;
/*
 * @author Anthony Olawo 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import objects.GameObject;
import stage.Game;

public class Parser {
	private FileOutputStream fileOut;
	private FileInputStream fileIn;
	private ObjectOutputStream out; 
	private ObjectInputStream in; 

	/*
	 * @param obj An object to be written to file
	 * @param url String referencing location to store the game object
	 * @return Nothing
	 */
	public void write(Object obj, String url) throws IOException{ 
		fileOut = new FileOutputStream(url);
		out = new ObjectOutputStream(fileOut); 
		out.writeObject(obj);
		out.close(); 
		fileOut.close(); 	
	}
	
	/*
	 * 
	 * @param url String referencing location to store the game object
	 * @return Nothing
	 */
	public Game read(String url) throws Exception{
		fileIn = new FileInputStream(url); 
		in = new ObjectInputStream(fileIn);
		Game game = (Game) in.readObject(); 
		
		fileIn.close(); 
		in.close();	
		return game; 
	}
	
	public void writeXML(){
		
	}
	
	public void readXML(){
		
		
	}
	
}
