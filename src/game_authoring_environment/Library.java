package game_authoring_environment;

import java.awt.Dimension;

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
		this.addTab( "Scenes", myScenesPanel);
		this.addTab( "Actors", myActorsPanel);
		this.addTab( "Media", myMediaPanel);
		this.addTab( "Behaviors", myBehaviorsPanel);
		
	}

	private void makePanels() {
		myScenesPanel = ViewFactory.buildPanel(PanelType.SCENE);
		myMediaPanel = ViewFactory.buildPanel(PanelType.MEDIA);
		myBehaviorsPanel = ViewFactory.buildPanel(PanelType.BEHAVIORS);
		myActorsPanel = ViewFactory.buildPanel(PanelType.ACTORS);
		
	}
}
