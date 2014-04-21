package game_authoring_environment;

/*
 * @author Anthony Olawo 
 * 
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AboutPanel extends JPanel {
	private JLabel label;
	private String htmlString;
	private JPanel about_Panel; 
	private JScrollPane helpPageScrollPane; 
	private JMenu help; 
	private JMenuItem getHelpPage; 
	
	public AboutPanel() {	
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		try {
			htmlString = readFile("about.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		helpPageScrollPane = new JScrollPane(setUpLabel(label));
		about_Panel = setUpPanel(about_Panel, helpPageScrollPane);

		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(Box.createRigidArea(new Dimension(10,0)));
		add(about_Panel);
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
				BorderFactory.createTitledBorder("Help"),
				BorderFactory.createEmptyBorder(10,10,10,10)));
		panel.add(scrollPane);
		
		return panel; 
	}

	
	private String readFile(String fileName) throws IOException {
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

	public void createAndShowHelpPanel() {
		//Create and set up the window.
		JFrame frame = new JFrame("Help");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Add content to the window.
		frame.add(new AboutPanel());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}