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
	private static Attributes attributesPane;
	private static Library libraryPane;
	
	public LeftPanel(GAEController gController){

	setOrientation(VERTICAL_SPLIT);
	libraryPane = new Library(gController);
	attributesPane = new Attributes(gController);
	setTopComponent(libraryPane);
	setBottomComponent(attributesPane);
	setPreferredSize(new Dimension(LEFT_PANEL_WIDTH,LEFT_PANEL_HEIGHT));
	}
	
	public HashMap<String, JPanel> setUpMap(){
		HashMap<String, JPanel> map = new HashMap<String, JPanel>();
		map = attributesPane.setUpMap(map);
		map = libraryPane.setUpMap(map);
		return map;
	}
	
}
