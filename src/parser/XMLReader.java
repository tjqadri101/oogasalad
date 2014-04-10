package parser;
/*
 * author Anthony Olawo 
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import saladConstants.SaladConstants;

public class XMLReader extends SuperXML{
	private List<String> attributes; 
	private NodeList myNodeList; 
	private SaladConstants mySaladConstants;
	
	public XMLReader(){ 
		attributes = new ArrayList<String>(); 
		mySaladConstants = new SaladConstants(); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see parser.SuperXML#read(java.lang.String)
	 * 
	 */
	@Override
	public List<String> read(String url){
		myNodeList = parseNodeList(url); 
		Node node; 
		Element element; 
		for(int i=0; i<myNodeList.getLength(); i++){ 
			System.out.println("Nodelist length: "+myNodeList.getLength()); 
			node = myNodeList.item(i); 
			element = (Element)node; 
			attributes.add(element.getElementsByTagName(
					mySaladConstants.ELEMENT_LABEL+"_"+i).item(0).getTextContent());  
		}
		return attributes; 
	}
	/*
	 * @param url A string containing URL of file to be read
	 * @return NodeList NodeList containing all the file's elements
	 */
	protected NodeList parseNodeList(String url){
		Document temp = initDocument(url); 
		temp.getDocumentElement().normalize(); 
		return temp.getElementsByTagName(mySaladConstants.ROOT_ELEMENT_LABEL); 
	}
	
}
