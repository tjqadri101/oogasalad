package parser;

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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node; 

public class ParseGame {
	private static final String ROOT_ELEMENT = "GameObjects";
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
	private static final String [] PARAMETER_NAMES  = {"Key", "Type_0", "Parameter_0", "Type_1", "Parameter_1", "Parameter_2", "Parameter_3","Type_2" };

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

	public File writeToFile(ArrayList<String> gameObjects, File fileToWriteTo) throws ParserConfigurationException{
		Element rootElement = doc.createElement(ROOT_ELEMENT);
		doc.appendChild(rootElement);

		Element child, grandChild;

		String[] parameters; 
		for(String gameObject: gameObjects){
			parameters = tokenizeString(gameObject);
			Element gameObjectKey = doc.createElement(PARAMETER_NAMES[0]);
			rootElement.appendChild(gameObjectKey);
			keys.add(parameters[0]); 
			for(int i = 1; i<parameters.length; i++){
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

	public ArrayList<String> readFromFile(File fileToReadFrom) throws ParserConfigurationException, SAXException, IOException{
		doc = docBuilder.parse (fileToReadFrom);
		doc.getDocumentElement ().normalize ();

		ArrayList<String> gameObjects = new ArrayList<String>(); 

		NodeList gameObjectNodes = doc.getElementsByTagName(PARAMETER_NAMES[0]);
		String gameObject = "";
		for(int i=0; i<gameObjectNodes.getLength(); i++){
			Node node = gameObjectNodes.item(i);
			gameObject= composeObjectString(gameObject, node.getNodeName(),i, i+2);

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
	public static void main (String [] args) throws ParserConfigurationException, SAXException, IOException{
		File in = new File("Game.xml"); 
		File out = new File("Game.xml"); 

		ParseGame a = new ParseGame(); 
		String actor = "Mario,ID,Mario_Parameter1,IMG_Url,Mario_Parameter2,Mario_Parameter3, Mario_Parameter4";
		ArrayList<String> gameObjects = new ArrayList<String>(); 
		gameObjects.add(actor); 
		a.writeToFile(gameObjects, out); 

		ArrayList<String> gameObjects_1 = new ArrayList<String>(); 
		gameObjects_1 = a.readFromFile(in); 
		for(String gameObject: gameObjects_1){
			System.out.println(gameObject);
		}
	}

}