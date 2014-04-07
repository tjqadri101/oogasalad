package parser;
/**
 * 
 * @author AnthonyOlawo
 *
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import jgame.JGColor;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node; 

import stage.*;
import objects.*; 

public class ParseGame {
	private static final String GAME_ELEMENT = "GameElement";
	private static final String ROOT_ELEMENT = "Game";
	private DocumentBuilderFactory docFactory; 
	private DocumentBuilder docBuilder; 
	private Document doc; 
	private TransformerFactory transformerFactory ;
	private Transformer transformer ;
	private DOMSource source;
	private StreamResult result;
	private File fileToReadFrom; 
	private File fileToWriteTo; 
	private Map<String, String> values; 
	private List<String> keys; 
	private static final String [] PARAMETER_NAMES  = {"Key", "Type_0", "Parameter_0", "Type_1", "Parameter_1", "Parameter_2", "Parameter_3","Type_2", "Parameter_4", "Parameter_5","Type_3","Parameter_6"  };

	public  ParseGame(){
		docFactory = DocumentBuilderFactory.newInstance();

		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doc = docBuilder.newDocument();
		transformerFactory = TransformerFactory.newInstance();

		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		source = new DOMSource(doc);
		keys = new ArrayList<String>();
	}

	public String[] tokenizeString (String arg){
		return arg.split("\\,"); 
	}

	public void writeToFile(Game game, String url) throws ParserConfigurationException{
		List<String> gameAttributes = new ArrayList<String>(); 
		gameAttributes = game.getAttributes(); 

		writeIndividualElement(gameAttributes, new File(url)); 
	}

	private File writeIndividualElement(List<String> gameAttributes, File fileToWriteTo) throws ParserConfigurationException{
		Element rootElement = doc.createElement(ROOT_ELEMENT);
		doc.appendChild(rootElement);

		Element child, grandChild;

		String[] parameters; 
		for(String gameObject: gameAttributes){
			parameters = tokenizeString(gameObject);
			Element gameObjectKey = doc.createElement(GAME_ELEMENT);
			rootElement.appendChild(gameObjectKey);

			for(int i = 0; i<parameters.length; i++){
				child = doc.createElement(PARAMETER_NAMES[i]); 
				child.appendChild(doc.createTextNode(parameters[i]));
				gameObjectKey.appendChild(child); 
			}
		}

		result = new StreamResult(fileToWriteTo);

		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileToWriteTo; 
	}

	public ArrayList<String> readFromFile(String urlOfFileToReadFrom) throws ParserConfigurationException, SAXException, IOException{
		doc = docBuilder.parse (new File(urlOfFileToReadFrom));
		doc.getDocumentElement ().normalize ();

		ArrayList<String> gameObjects = new ArrayList<String>(); 

		NodeList gameObjectNodes = doc.getElementsByTagName(GAME_ELEMENT);
		String gameObject = "";
		for(int i=0; i<gameObjectNodes.getLength(); i++){
			Node node = gameObjectNodes.item(i);

			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) node; 
				for(int j=0; j<PARAMETER_NAMES.length; j++){
					if(element.getElementsByTagName(PARAMETER_NAMES[j]).item(0) != null){
						gameObject = composeObjectString(gameObject, element.getElementsByTagName(PARAMETER_NAMES[j]).item(0).getTextContent(), j, PARAMETER_NAMES.length);
						System.out.println(element.getElementsByTagName(PARAMETER_NAMES[j]).item(0).getTextContent());
					}
				}
			}
			gameObjects.add(gameObject); 
		}

		return gameObjects;
	}

	public String composeObjectString(String gameObject, String token, int currentTokenIndex, int lastTokenIndex){
		gameObject +=token; 
		if(lastTokenIndex - currentTokenIndex>1){
			gameObject +=",";
		}
		return gameObject; 
	}

	public static void main (String [] args) throws ParserConfigurationException{
		ParseGame A = new ParseGame() ; 
		Game myGame = new Game(); 
		Level myLevel = new Level(0); 
		Scene myScene = new Scene(0); 
		JGColor myColor = new JGColor(0,0,0);
		String player = "myPlayer";
		int id = 0; 
		double xpos = 0.0, ypos= 0.0; 
		//Player myPlayer = new Player(player, xpos, ypos, id , myColor); 

		myGame.addLevel(myLevel);
		myGame.addScene(1, myScene); 
		//myGame.addObject(0, 0, myPlayer);

		List<String> gameAttributes = new ArrayList<String>();
		gameAttributes = myGame.getAttributes();

		A.writeToFile(myGame, "Game.xml"); 
		for(String string: gameAttributes)
			System.out.println(string);
		
		
	}


}