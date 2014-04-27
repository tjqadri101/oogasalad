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

public class InfoPanel extends JPanel {
	private JLabel myLabel;
	protected String htmlString;
	private JPanel myPanel; 
	private JScrollPane myScrollPane; 
	
	public InfoPanel() {	
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setUpHTMLString(); 
		myScrollPane = new JScrollPane(setUpLabel(myLabel));
		myPanel = setUpPanel(myPanel, myScrollPane);

		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(Box.createRigidArea(new Dimension(10,0)));
		add(myPanel);
	}
	
	protected void setUpHTMLString(){ 
	
	}
	
	protected String selectHTMLFile(){
		return ""; 
	}
	
	private JLabel setUpLabel(JLabel label){
		label = new JLabel(htmlString);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label; 
	}
	
	private JPanel setUpPanel(JPanel panel, JScrollPane scrollPane){
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(10,10,10,10)));
		panel.add(scrollPane);
		
		return panel; 
	}

	
	protected String readFile(String fileName) throws IOException {
		System.out.println("file name: "+fileName); 
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

	public void createAndShowInfoPanel() {
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