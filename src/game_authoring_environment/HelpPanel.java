package game_authoring_environment;

import java.io.IOException;

import saladConstants.SaladConstants;

/*
 * @author Anthony Olawo 
 * 
 */

public class HelpPanel extends InfoPanel {
	
	public HelpPanel() {
		super(); 
	}
	
	@Override
	protected String selectHTMLFile(){ 
		return SaladConstants.HELP_HTML_URL; 
	}

	
	public void createAndShowHelpPanel() {
		super.createAndShowInfoPanel(new HelpPanel()); 
	}

}
