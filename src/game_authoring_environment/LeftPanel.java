package game_authoring_environment;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import controller.GAEController;


public class LeftPanel extends JSplitPane {
	
	private static final int FULL_VIEW_HEIGHT = 768;
	private static final int FULL_VIEW_WIDTH = 1024;
	private static final int LEFT_PANEL_HEIGHT = FULL_VIEW_HEIGHT;
	private static final int LEFT_PANEL_WIDTH = FULL_VIEW_WIDTH * 1/5+70;
	private static AttributesPanel attributesPane;
	private static Library libraryPane;
	private static HashMap<String, JPanel> map;
	
	public LeftPanel(GAEController gController){

	setOrientation(VERTICAL_SPLIT);
	libraryPane = new Library(gController);
	attributesPane = new AttributesPanel(gController);
	setTopComponent(libraryPane);
	setBottomComponent(attributesPane);
	setPreferredSize(new Dimension(LEFT_PANEL_WIDTH,LEFT_PANEL_HEIGHT));
	}
	
	public HashMap<String, JPanel> setUpMap(){
		map = new HashMap<String, JPanel>();
		map = attributesPane.setUpMap(map);
		map = libraryPane.setUpMap(map);
		return map;
	}
	
	public AttributesPanel getAttributes(){
		return attributesPane;
	}

	public Library getLibrary() {
		return libraryPane;
	}
}
