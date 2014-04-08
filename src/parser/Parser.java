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

	public void write(Object obj, String url) throws IOException{ 
		fileOut = new FileOutputStream(url);
		out = new ObjectOutputStream(fileOut); 
		out.writeObject(obj);
		out.close(); 
		fileOut.close(); 	
	}
	
	public Game read(String url) throws Exception{
		fileIn = new FileInputStream(url); 
		in = new ObjectInputStream(fileIn);
		Game game = (Game) in.readObject(); 
		
		fileIn.close(); 
		in.close();	
		return game; 
	}
	
	
}
