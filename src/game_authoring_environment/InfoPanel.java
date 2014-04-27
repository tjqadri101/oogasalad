package game_authoring_environment;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class InfoPanel extends JPanel{
	protected JLabel myLabel;
	protected JPanel myPanel; 
	protected JScrollPane myScrollPane; 
	protected String htmlString;
	
	public InfoPanel(){
		setUpHTMLString(); 
		setUpInfoPanel(); 
	}
	
	protected void setUpInfoPanel(){ 
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));	 
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
	
	public void createAndShowInfoPanel(InfoPanel panel) {
		//Create and set up the window.
		JFrame frame = new JFrame("OOGASalad by iTeam");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Add content to the window.
		frame.add(panel);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}