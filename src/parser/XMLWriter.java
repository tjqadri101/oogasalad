package parser;
/*
 * @author Anthony Olawo 
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import saladConstants.SaladConstants;


public class XMLWriter {
	private XStream myXStream;
	private String xml;
	private PrintWriter out; 
	
	public XMLWriter(){ 
		myXStream = new XStream(new DomDriver()); 
	}
		
	/*
	 *@param attributes A list of strings to be written to file.
	 *@param url URL of the file to be written to.
	 */
	public void write(List<String> attributes, String url) {		
		 try {
			out = new PrintWriter(url);
			xml = myXStream.toXML(attributes);
			out.write(xml); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 out.close(); 
	}
	
}
