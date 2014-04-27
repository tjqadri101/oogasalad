package util;

/*
 * @author Anthony Olawo
 * 
 * This class avails methods to initialise InfoPanel, which extends it. 
 */

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class SuperInfoPanel extends JPanel{
	protected JLabel myLabel;
	protected JPanel myPanel; 
	protected JScrollPane myScrollPane; 
	protected String htmlString; 
	protected JFrame myFrame;
	protected String title; 


	/**
	 * 	Fully initialises the SuperInfoPanel.  
	 */
	protected void setUpInfoPanel(){ 
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));	 
		myScrollPane = new JScrollPane(setUpLabel(myLabel));
		myPanel = setUpPanel(myPanel, myScrollPane);
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(Box.createRigidArea(new Dimension(10,0)));
		add(myPanel);
	}

	/**
	 * Parses the HTML file to be displayed in the SuperInfoPanel. 
	 */
	protected void setUpHTMLString(){ 
		try {
			htmlString = readFile(getHTMLFileURL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the url of HTML file to be read 
	 * @return String url of the HTML file whose contents are to be displayed in the SuperInfoPanel. 
	 */
	protected String getHTMLFileURL(){ 
		return "";
	}

	/**
	 * Sets up JLabel that will hold HTML formatted content
	 * 
	 * @param label JLabel that will hold the html formated information. 
	 * @return
	 */
	protected JLabel setUpLabel(JLabel label){
		label = new JLabel(htmlString);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label; 
	}

	/**
	 * Sets up SuperInfoPanel.
	 * 
	 * @param panel JPanel that will hold scrollPane
	 * @param scrollPane 
	 * @return  JPanel with a scroll pane, box layout and borders. 
	 */
	protected JPanel setUpPanel(JPanel panel, JScrollPane scrollPane){
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(10,10,10,10)));
		panel.add(scrollPane);

		return panel; 
	}

	/**
	 * A basic parser that reads a HTML file and returns a string with its contents
	 * 
	 * @param fileName URL of HTML file to be parsed.
	 * @return String containing HTML file's parsed contents.
	 * @throws IOException
	 */
	protected String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	/**
	 * Adds a ready SuperInfoPanel to a JFrame. 
	 * 
	 * @param panel A SuperInfoPanel ready to be added to a JFrame
	 */

	protected void createInfoPanel(SuperInfoPanel panel){
		//Create and set up the window.
		myFrame = new JFrame(title);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Add content to the window.
		myFrame.add(panel);

		//Display the window.
		myFrame.pack();
	}

	/**
	 * Makes JFrame containing ready SuperInfoPanel visible
	 */
	public void showInfoPanel(){
		myFrame.setVisible(true);
	}

}
