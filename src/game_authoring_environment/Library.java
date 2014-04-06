package game_authoring_environment;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Library extends JTabbedPane {
	
	private static final int FULL_VIEW_HEIGHT = 768;
	private static final int FULL_VIEW_WIDTH = 1024;
	private static final int LIBRARY_PANEL_HEIGHT = FULL_VIEW_HEIGHT * 1/2;
	private static final int LIBRARY_PANEL_WIDTH = FULL_VIEW_WIDTH * 1/5;
	
	private JPanel myScenesPanel;
	private JPanel myMediaPanel;
	private JPanel myBehaviorsPanel;
	private JPanel myActorsPanel;

	public Library(){
		setPreferredSize(new Dimension(LIBRARY_PANEL_WIDTH, LIBRARY_PANEL_HEIGHT));
		makePanels();
		addTabs();
		
	}

	private void addTabs() {
		ImageIcon sceneTabIcon = new ImageIcon(this.getClass().getResource("resources/sceneIcon.png"));
		ImageIcon actorsTabIcon = new ImageIcon(this.getClass().getResource("resources/actorsIcon.png"));
		ImageIcon mediaTabIcon = new ImageIcon(this.getClass().getResource("resources/mediaIcon.png"));
		ImageIcon behaviorsTabIcon = new ImageIcon(this.getClass().getResource("resources/behaviorsIcon.png"));
		this.addTab("",sceneTabIcon, myScenesPanel);
		this.addTab( "", actorsTabIcon, myActorsPanel);
		this.addTab( "", mediaTabIcon, myMediaPanel);
		this.addTab( "", behaviorsTabIcon, myBehaviorsPanel);
		
	}

	private void makePanels() {
		myScenesPanel = ViewFactory.buildPanel(PanelType.SCENE);
		myMediaPanel = ViewFactory.buildPanel(PanelType.MEDIA);
		myBehaviorsPanel = ViewFactory.buildPanel(PanelType.BEHAVIORS);
		myActorsPanel = ViewFactory.buildPanel(PanelType.ACTORS);
		
	}
}
