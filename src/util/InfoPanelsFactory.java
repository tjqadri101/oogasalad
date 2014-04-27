package util;

/*
 * @author Anthony Olawo
 * 
 * This class avails methods allowing one to create InfoPanels
 */

import java.util.HashMap;
import java.util.Map;

public class InfoPanelsFactory {
	protected  Map<String, InfoPanel> myInfoPanels;
	
	
	public InfoPanelsFactory(){
		myInfoPanels= new HashMap<String, InfoPanel>();
	}
	
	/**
	 * Constructs an info panel 
	 * 
	 * @param name Name of the info panel to be constructed
	 * @param url url of HTML file whose contents will be displayed on the info panel 
	 * @return
	 */
	public InfoPanel constructPanel(String name, String url){ 
		InfoPanel myInfoPanel = new InfoPanel(name, url);
		myInfoPanels.put(name, myInfoPanel);
		return myInfoPanel; 
	}
	
}
