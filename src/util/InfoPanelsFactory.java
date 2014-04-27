package util;

/*
 * @author Anthony Olawo
 */

import java.util.HashMap;
import java.util.Map;

public class InfoPanelsFactory {
	private  Map<String, InfoPanel> myInfoPanels;
	
	
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
	
	/**
	 * Displays the info panel with the give name. 
	 * 
	 * @param name Name of the info panel to be displayed
	 */
	public void displayInfoPanel(String name){
		InfoPanel myInfoPanel = myInfoPanels.get(name);
		myInfoPanel.showInfoPanel(); 
	}
	
	/**
	 * Constructs and displays the info panel with the given name
	 * 
	 * @param name Name of the info panel 
	 * @param url URL of the HTML file whose contents will be displayed on the info panel 
	 * @return
	 */
	public InfoPanel constructAndDisplayInfoPanel(String name, String url){
		InfoPanel myInfoPanel = new InfoPanel(name, url);
		myInfoPanels.put(name, myInfoPanel);
		myInfoPanel.showInfoPanel(); 
		return myInfoPanel; 
	}
	
	/**
	 * Displays all the info panels created so far
	 * 
	 */
	
	public void displayAllInfoPanels(){
		for(String name: myInfoPanels.keySet()){
			myInfoPanels.get(name).showInfoPanel(); 
		}
	}
	
	/**
	 * Returns a reference to the info panel stored under the given name 
	 * 
	 * @param name Name of the info panel 
	 * @return InfoPanel object stored under the given name
	 */
	public InfoPanel getInfoPanel(String name){
		return myInfoPanels.get(name);
	}
	
}
