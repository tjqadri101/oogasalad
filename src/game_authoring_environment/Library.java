package game_authoring_environment;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import saladConstants.SaladConstants;
import controller.GAEController;

public class Library extends JTabbedPane {
	
	private static final int FULL_VIEW_HEIGHT = 768;
	private static final int FULL_VIEW_WIDTH = 1024;
	private static final int LIBRARY_PANEL_HEIGHT = FULL_VIEW_HEIGHT * 1/2;
	private static final int LIBRARY_PANEL_WIDTH = FULL_VIEW_WIDTH * 1/5;
	
	private JPanel myScenesPanel;
	private JPanel myTilePanel;
	private JPanel myActorsPanel;
	private GAEController controller;

	public Library(GAEController gController){
		controller = gController;
		setPreferredSize(new Dimension(LIBRARY_PANEL_WIDTH, LIBRARY_PANEL_HEIGHT));
		makePanels(controller);
		addTabs();
	
	}

	private void addTabs() {
		ImageIcon sceneTabIcon = new ImageIcon(this.getClass().getResource("resources/sceneIcon.png"));
		ImageIcon actorsTabIcon = new ImageIcon(this.getClass().getResource("resources/actorsIcon.png"));
		ImageIcon behaviorsTabIcon = new ImageIcon(this.getClass().getResource("resources/behaviorsIcon.png"));
		this.addTab("",sceneTabIcon, myScenesPanel);
		this.addTab( "", actorsTabIcon, myActorsPanel);
		this.addTab( "", behaviorsTabIcon, myTilePanel);
		
	}

	private void makePanels(GAEController gController) {
		myScenesPanel = ViewFactory.buildPanel(PanelType.SCENE,gController);
		myTilePanel = ViewFactory.buildPanel(PanelType.TILE,gController);
		myActorsPanel = ViewFactory.buildPanel(PanelType.ACTORS,gController);
		
	}
	
	public HashMap<String, JPanel> setUpMap(HashMap<String, JPanel> map){
		map.put(SaladConstants.TILE_PANEL, myTilePanel);
		map.put(SaladConstants.SCENE_PANEL, myScenesPanel);
		map.put(SaladConstants.ACTOR_PANEL, myActorsPanel);
		return map;
	}

	public void setTab(int index) {
		this.setSelectedIndex(index);		
	}
	
}
