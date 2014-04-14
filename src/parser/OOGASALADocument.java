package parser;
/*
 * @author Anthony Olawo 
 */
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class OOGASALADocument {
	private DocumentBuilderFactory myDocumentFactory; 
	private DocumentBuilder myDocumentBuilder; 
	private Document myDocument; 
	private File myFile; 
	
	public OOGASALADocument(){
		initDocument(); 
	}
	
	public OOGASALADocument(String url){
		initDocument(url); 
	}
	
	/*
	 * Initialises a document.
	 */
	private void initDocument( ){ 
		initDocumentBuilder();  
		myDocument = myDocumentBuilder.newDocument(); 
	}
	
	
	private void initDocumentBuilder(){ 
		myDocumentFactory = DocumentBuilderFactory.newInstance(); 
		try {
			myDocumentBuilder = myDocumentFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
		} 
	}
	/*
	 * Initialises a document from a file. (One can then read from the file underneath)
	 * @param url A string containing a file url.  
	 */
	private void initDocument(String url) { 
		myFile = new File(url); 
		initDocumentBuilder(); 
		try {
			myDocument = myDocumentBuilder.parse(myFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} 
	}
	
	/*
	 * @return Document The initialised document.  
	 */
	public Document getOOGASALADocument(){ 
		return myDocument; 
	}
}
