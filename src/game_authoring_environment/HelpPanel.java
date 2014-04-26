package game_authoring_environment;

/*
 * @author Anthony Olawo 
 * 
 */

import javax.swing.*;

import saladConstants.SaladConstants;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelpPanel extends InfoPanels {
	
	public HelpPanel() {	
		super(); 
	}
	
	@Override
	protected String selectHTMLFile(){ 
		System.out.println(SaladConstants.HELP_HTML_URL); 
		return SaladConstants.HELP_HTML_URL; 
	}

	public void createAndShowHelpPanel() {
		super.createAndShowPanel();
	}

}