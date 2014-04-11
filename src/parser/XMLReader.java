package parser;
/*
 * author Anthony Olawo 
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import saladConstants.SaladConstants;

public class XMLReader{
	private OOGASALADocument myOOGASALADocument; 
	protected Document myDocument; 
	protected SaladConstants mySaladConstants; 
	private List<String> attributes; 
	private NodeList myNodeList; 

	public XMLReader(){ 
		attributes = new ArrayList<String>(); 
		mySaladConstants = new SaladConstants();
	}
	
	/*
	 *@param url URL of file to read from 
	 *@return List<String> List of strings read from file 
	 */
	public List<String> read(String url){
		myNodeList = parseNodeList(url); 
		Node node; 
		Element element; 
		for(int i=0; i<myNodeList.getLength(); i++){ 
			System.out.println("Nodelist length: "+myNodeList.getLength()); 
			node = myNodeList.item(i); 
			element = (Element)node; 
			System.out.println(element.getTextContent()); 
			attributes.add(element.getTextContent());  
		}
		return attributes; 
	}
	/*
	 * @param url A string containing URL of file to be read
	 * @return NodeList NodeList containing all the file's elements
	 */
	protected NodeList parseNodeList(String url){
		initOOGASALADocument(url); 
		myDocument.getDocumentElement().normalize(); 
		return myDocument.getElementsByTagName(mySaladConstants.ELEMENT_LABEL); 
	}
	
	/*
	 * Initialises OOGASALADocument and returns OOGASALADocument.myDocument
	 */
	
	private void initOOGASALADocument(String url){ 
		myOOGASALADocument = new OOGASALADocument(url); 
		myDocument = myOOGASALADocument.getOOGASALADocument(); 
	}
}
