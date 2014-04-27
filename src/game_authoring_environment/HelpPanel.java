package game_authoring_environment;

import java.io.IOException;

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
		return "./src/game_authoring_environment/resources/help.html"; 
	}

	
	public void createAndShowHelpPanel() {
		super.createAndShowInfoPanel(new HelpPanel()); 
	}

}
