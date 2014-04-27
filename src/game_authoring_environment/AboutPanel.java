package game_authoring_environment;

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
		return "./src/game_authoring_environment/resources/about.html"; 
	}

	public void createAndShowAboutPanel() {
		super.createAndShowInfoPanel(new AboutPanel()); 	
	}

}
