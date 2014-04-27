package util;

import java.io.IOException;

import saladConstants.SaladConstants;

/*
 * @author Anthony Olawo 
 * 
 */

public class InfoPanel extends SuperInfoPanel {
	private String fileURL; 
	private String fileName;


	public InfoPanel(String fileName, String fileURL){
		createInfoPanel(fileName, fileURL);
	}

	@Override
	protected String getHTMLFileURL(){ 
		return fileURL; 
	}

	private void selectHTMLFileURLAndName(String fileName, String fileURL){
		this.fileURL = fileURL; 
		this.fileName = fileName;  
	}

	private void createInfoPanel(String fileName, String fileURL){
		selectHTMLFileURLAndName(fileName, fileURL);
		setUpHTMLString(); 
		setUpInfoPanel(); 
		super.createInfoPanel(this); 
	}
	
	public void showInfoPanel(){
		super.showInfoPanel(); 
	}
	
	public void createAndShowInfoPanel(String fileName, String fileURL) {
		createInfoPanel(fileName, fileURL); 
		showInfoPanel(); 
	}

}
