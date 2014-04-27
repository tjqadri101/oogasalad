package game_authoring_environment;

import java.io.IOException;

import javax.swing.JFrame;

import saladConstants.SaladConstants;

/*
 * @author Anthony Olawo 
 * 
 */

public class HelpPanel extends InfoPanel {

	public HelpPanel() {	
	}

	@Override
	protected void setUpHTMLString(){ 
		try {
			htmlString = readFile(selectHTMLFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(htmlString); 
	}

	@Override
	protected String selectHTMLFile(){
		return SaladConstants.HELP_HTML_URL; 
	}

	public void createAndShowHelpPanel() {
		//Create and set up the window.
		JFrame frame = new JFrame("OOGASalad by iTeam");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Add content to the window.
		frame.add(new InfoPanel());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}