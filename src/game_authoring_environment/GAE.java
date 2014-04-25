package game_authoring_environment;

import java.awt.BorderLayout;
import controller.GAEController;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.plaf.LayerUI;

public class GAE {
	
	public static final String TITLE = "OOGASalad iTeam";
	private static FullView fv;
	private static MenuBar mb; 
	private static GAEController gController;
	private LayerUI<JComponent> layerUI;
	
	public GAE(GAEController gController){
		JFrame mainFrame = makeFrame(TITLE);
		layerUI = new GAELayerUI();		
		fv = new FullView(gController);
		mb = new MenuBar(gController);	
		JLayer<JComponent> jLayer = new JLayer<JComponent>(fv,layerUI);
		mainFrame.setJMenuBar(mb);
		mainFrame.add(jLayer);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private static JFrame makeFrame(String title){
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
	
	public FullView getFullView(){
		return fv;
	}
	
	public MenuBar getMenuBar(){
		return mb;
	}
	
}
