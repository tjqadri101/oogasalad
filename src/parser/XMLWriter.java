package parser;
/*
 * @author Anthony Olawo 
 */
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import saladConstants.SaladConstants;


public class XMLWriter {
	private OOGASALADocument myOOGASALADocument; 
	private Document myDocument; 
	protected SaladConstants mySaladConstants; 
	private TransformerFactory myTransformerFactory; 
	private Transformer myTransformer; 
	private DOMSource mySource;
	private StreamResult myStreamResult;
	
	public XMLWriter(){ 
		myOOGASALADocument = new OOGASALADocument(); 
		myDocument = myOOGASALADocument.getOOGASALADocument(); 
		initSource(); 
		initTransformer(); 
	}
	
	
	
	/*
	 *@param attributes A list of strings to be written to file.
	 *@param url URL of the file to be written to.
	 */
	public void write(List<String> attributes, String url){
		myStreamResult = initStreamResult(url); 
		Element root = myDocument.createElement(mySaladConstants.ROOT_ELEMENT_LABEL); 
		myDocument.appendChild(root); 
		Element node; 
		String attribute; 
		for(int i=0; i<attributes.size(); i++){
			attribute = attributes.get(i); 
			node = myDocument.createElement(mySaladConstants.ELEMENT_LABEL);
			node.appendChild(myDocument.createTextNode(attribute)); 
			root.appendChild(node); 		
		}
		
		transform(); 
	}
	
	/*Transorms the DOMSource mySource to StreamResult mySource. 
	 * 
	 */
	private void transform(){
		try {
			myTransformer.transform(mySource, myStreamResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
		} 
	}
	
	/*
	 * Initialises DOMSource mySource from the document myDocument. 
	 */
	private void initSource(){	
		mySource = new DOMSource(myDocument); 
	}
	
	/*
	 * Initialises myTransformer from myTransformerFactory
	 */
	
	private void initTransformer(){ 
		myTransformerFactory = TransformerFactory.newInstance(); 
		try {
			myTransformer = myTransformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
		}
	}
	
	/*
	 * Initialises a streamResult from url. 
	 * @param url URL that the StreamResult will point to.
	 * @return StreamResult Initialised StreamResult.
	 */
	
	private StreamResult initStreamResult(String url){
		myStreamResult = new StreamResult(url);
		return myStreamResult; 
	}
	
	

}
