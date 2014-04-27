package util;

import java.io.IOException;

import saladConstants.SaladConstants;

/*
 * @author Anthony Olawo 
 * 
 * This class avails methods to create, modify and display an instance of InfoPanel 
 * 
 */

public class InfoPanel extends SuperInfoPanel {
	private String fileURL; 

	/**
	 * 
	 * @param title Title of the InfoPanel to be created. Used as a key when storing the panel
	 * @param fileURL URL of the HTML file whose contents will be displayed on the panel
	 */
	public InfoPanel(String fileName, String title){
		createInfoPanel(fileName, title);
	}


	@Override
	protected String getHTMLFileURL(){ 
		return fileURL; 
	}

	/**
	 * 
	 * Sets fileName and fileURL of the InfoPanel
	 * 
	 * @param title Title of the InfoPanel to be created. Used as a key when storing the panel
	 * @param fileURL URL of the HTML file whose contents will be displayed on the panel
	 */
	private void setHTMLFileURLAndName(String fileName, String title){
		this.fileURL = fileURL; 
		this.title = title;  
	}

	/**
	 * Creates the InfoPanel
	 * 
	 * @param title Title of the InfoPanel to be created. Used as a key when storing the panel
	 * @param fileURL URL of the HTML file whose contents will be displayed on the panel
	 */
	private void createInfoPanel(String title, String fileURL){
		setHTMLFileURLAndName(title, fileURL);
		setUpHTMLString(); 
		setUpInfoPanel(); 
		super.createInfoPanel(this); 
	}
	/**
	 * Makes the created info panel visible. 
	 */
	public void showInfoPanel(){
		super.showInfoPanel(); 
	}

	/**
	 * Creates the InfoPanel and makes it visible 
	 * 
	 * @param title Title of the InfoPanel to be created. Used as a key when storing the panel
	 * @param fileURL URL of the HTML file whose contents will be displayed on the panel
	 */

	public void createAndShowInfoPanel(String title, String fileURL) {
		createInfoPanel(title, fileURL); 
		showInfoPanel(); 
	}

}
