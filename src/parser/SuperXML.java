package parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import saladConstants.SaladConstants;


public class SuperXML {
	private DocumentBuilderFactory myDocumentFactory; 
	private DocumentBuilder myDocumentBuilder; 
	protected Document myWriterDocument, myReaderDocument; 
	private TransformerFactory myTransformerFactory; 
	protected Transformer myTransformer; 
	protected DOMSource mySource;
	protected StreamResult myStreamResult; 
	private File fileToReadFrom;
	protected NodeList myNodeList;
	
	private SaladConstants mySaladConstants; 

	
	public SuperXML(){
		init(); 
	}

	private void init(){
		initDocument(); 
		initSource(); 
	}

	private void initDocument(){
		myDocumentFactory = DocumentBuilderFactory.newInstance(); 
		try {
			myDocumentBuilder = myDocumentFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
		} 
		myWriterDocument = myDocumentBuilder.newDocument(); 
	}
	
	protected Document initDocument(String url){
		fileToReadFrom = new File(url); 
		try {
			myReaderDocument = myDocumentBuilder.parse(fileToReadFrom);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} 
		return myReaderDocument; 
	}
	
	private void initSource(){
		myTransformerFactory = TransformerFactory.newInstance(); 
		try {
			myTransformer = myTransformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
		}
		mySource = new DOMSource(myWriterDocument); 
	}

	
	

	
	protected List<String> read(String url){ 
		
		return null ; 
	}
	
	protected void write(List<String> attributes, String url){ 
		
	}
		
}
