package game_authoring_environment;

import saladConstants.SaladConstants;

/*
 * @author Anthony Olawo 
 * 
 */


public class AboutPanel extends InfoPanel {

	public AboutPanel() {
		super();  
	}

	@Override
	protected String selectHTMLFile(){ 
		return SaladConstants.ABOUT_HTML_URL; 
	}

	public void createAndShowAboutPanel() {
		super.createAndShowInfoPanel(new AboutPanel()); 	
	}

}
