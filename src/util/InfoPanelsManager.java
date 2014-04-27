package util;
/*
 * @ author Anthony Olawo 
 * 
 * This class avails a number of methods to manage already created InfoPanels. 
 */


public class InfoPanelsManager extends InfoPanelsFactory {
	
	public InfoPanelsManager() {
	}
	
	/**
	 * Displays the info panel with the given title. 
	 * 
	 * @param title Title of the info panel to be displayed
	 */
	public void displayInfoPanel(String title){
		InfoPanel myInfoPanel = myInfoPanels.get(title);
		myInfoPanel.showInfoPanel(); 
	}
	
	/**
	 * Constructs and displays the info panel with the given title
	 * 
	 * @param title Title of the info panel 
	 * @param url URL of the HTML file whose contents will be displayed on the info panel 
	 * @return
	 */
	public InfoPanel constructAndDisplayInfoPanel(String title, String url){
		InfoPanel myInfoPanel = new InfoPanel(title, url);
		myInfoPanels.put(title, myInfoPanel);
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
	 * Returns a reference to the info panel stored under the given title. 
	 * 
	 * @param title Title of the info panel 
	 * @return InfoPanel object stored under the given name
	 */
	public InfoPanel getInfoPanel(String title){
		return myInfoPanels.get(title);
	}
	
	/**
	 * Deletes InfoPanel with the given title. 
	 * 
	 * @param title Title of the InfoPanel to be deleted
	 */
	public void deleteInfoPanel(String title){
		myInfoPanels.remove(title); 
	}
	
	/**
	 * Deletes all created InfoPanels
	 */
	public void deleteAllInfoPanels(){
		myInfoPanels.clear();
	}
}
