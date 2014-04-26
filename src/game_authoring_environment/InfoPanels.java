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

public class InfoPanels extends JPanel {
	protected JLabel myLabel;
	protected String htmlString;
	protected JPanel myPanel; 
	protected JScrollPane myScrollPane; 
	
	public InfoPanels() {	
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setUpHTMLString();  
		myScrollPane = new JScrollPane(setUpLabel(myLabel));
		myPanel = setUpPanel(myPanel, myScrollPane);

		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(Box.createRigidArea(new Dimension(10,0)));
		add(myPanel);
	}
	
	
	
	protected void setUpHTMLString(){
		try {
			htmlString = readFile(selectHTMLFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String selectHTMLFile(){ 
		return ""; 
	}
	
	protected JLabel setUpLabel(JLabel label){
		label = new JLabel(htmlString);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label; 
	}
	
	protected JPanel setUpPanel(JPanel panel, JScrollPane scrollPane){
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(10,10,10,10)));
		panel.add(scrollPane);
		
		return panel; 
	}

	
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

	protected void createAndShowPanel() {
		//Create and set up the window.
		JFrame frame = new JFrame("OOGASalad by iTeam");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Add content to the window.
		frame.add(new InfoPanels());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}