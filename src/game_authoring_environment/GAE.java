package game_authoring_environment;

import java.awt.BorderLayout;
import controller.GAEController;

import javax.swing.JFrame;

public class GAE {
	
	public static final String TITLE = "OOGASalad iTeam";
	
	public static void main(String args[]){
		JFrame mainFrame = makeFrame(TITLE);
		GAEController gController = new GAEController();
		FullView fv = new FullView(gController);
		MenuBar mb = new MenuBar(gController);
		mainFrame.add(fv, BorderLayout.CENTER);
		mainFrame.add(mb, BorderLayout.NORTH);
		mainFrame.pack();
		mainFrame.setVisible(true);
		System.out.println("help");
	}
	
	private static JFrame makeFrame(String title){
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

}
