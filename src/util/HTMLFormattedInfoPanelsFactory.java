package util;

/*
 * @author Anthony Olawo
 */

import java.util.HashMap;
import java.util.Map;

public class HTMLFormattedInfoPanelsFactory {
	private  Map<String, InfoPanel> myInfoPanels;
	
	public HTMLFormattedInfoPanelsFactory(){
		myInfoPanels= new HashMap<String, InfoPanel>();
	}
	
	public InfoPanel constructPanel(String name, String url){ 
		InfoPanel myInfoPanel = new InfoPanel(name, url);
		myInfoPanels.put(name, myInfoPanel);
		return myInfoPanel; 
	}
	
	public void displayInfoPanel(String name){
		InfoPanel myInfoPanel = myInfoPanels.get(name);
		myInfoPanel.showInfoPanel(); 
	}
	
	public InfoPanel constructAndDisplayInfoPanel(String name, String url){
		InfoPanel myInfoPanel = new InfoPanel(name, url);
		myInfoPanels.put(name, myInfoPanel);
		myInfoPanel.showInfoPanel(); 
		return myInfoPanel; 
	}
	
	public void displayAllInfoPanels(){
		for(String name: myInfoPanels.keySet()){
			myInfoPanels.get(name).showInfoPanel(); 
		}
	}
	
	public InfoPanel getInfoPanel(String name){
		return myInfoPanels.get(name);
	}
	
}
