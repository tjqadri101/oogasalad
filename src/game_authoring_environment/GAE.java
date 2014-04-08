package game_authoring_environment;

import java.awt.BorderLayout;
import controller.GAEController;

import javax.swing.JFrame;

public class GAE {
	
	public static final String TITLE = "OOGASalad iTeam";
	private static FullView fv;
	private static MenuBar mb; 
	private static GAEController gController;
	
	public GAE(GAEController gController){
		JFrame mainFrame = makeFrame(TITLE);
		fv = new FullView(gController);
		mb = new MenuBar(gController);
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
