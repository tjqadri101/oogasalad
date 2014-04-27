package parser;
/*
 * author Anthony Olawo 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import saladConstants.SaladConstants;

public class XMLReader{
	private String xml; 
	private XStream myXStream;
	private Scanner myScanner; 
	public XMLReader(){ 
		myXStream  = new XStream(new DomDriver());
	}
	
	/*
	 *@param url URL of file to read from 
	 *@return List<String> List of strings read from file 
	 */
	public List<String> read(String url) {
		try {
			myScanner =  new Scanner(new File(url));
			xml = myScanner.useDelimiter("\\Z").next();
			myScanner.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (List<String>) myXStream.fromXML(xml); 
	}

}
