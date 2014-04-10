package parser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import saladConstants.SaladConstants;

public class XMLWriter extends SuperXML{
	private SaladConstants mySaladConstants; 
	
	public XMLWriter(){ 
	
	}
	
	/*
	 * (non-Javadoc)
	 * @see parser.SuperXML#write(java.util.List, java.lang.String)
	 */
	@Override
	public void write(List<String> attributes, String url){
		myStreamResult = initStreamResult(url); 
		Element root = myWriterDocument.createElement(mySaladConstants.ROOT_ELEMENT_LABEL); 
		myWriterDocument.appendChild(root); 
		Element node; 
		String attribute; 
		for(int i=0; i<attributes.size(); i++){
			attribute = attributes.get(i); 
			node = myWriterDocument.createElement(mySaladConstants.ELEMENT_LABEL+"_"+i);
			node.appendChild(myWriterDocument.createTextNode(attribute)); 
			root.appendChild(node); 		
		}
		
		transform(); 
	}
	
	/*
	 * 
	 */
	private void transform(){
		try {
			myTransformer.transform(mySource, myStreamResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
		} 
	}
	
	protected StreamResult initStreamResult(String url){
		myStreamResult = new StreamResult(url);
		return myStreamResult; 
	}
	


}
