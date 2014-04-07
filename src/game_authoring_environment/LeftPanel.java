package game_authoring_environment;

import java.awt.Dimension;

import javax.swing.JSplitPane;

import controller.GAEController;


public class LeftPanel extends JSplitPane {
	
	private static final int FULL_VIEW_HEIGHT = 768;
	private static final int FULL_VIEW_WIDTH = 1024;
	private static final int LEFT_PANEL_HEIGHT = FULL_VIEW_HEIGHT;
	private static final int LEFT_PANEL_WIDTH = FULL_VIEW_WIDTH * 1/5+70;
	
	public LeftPanel(GAEController gController){

	setOrientation(VERTICAL_SPLIT);
	Library libraryPane = new Library(gController);
	Attributes attributesPane = new Attributes(gController);
	setTopComponent(libraryPane);
	setBottomComponent(attributesPane);
	setPreferredSize(new Dimension(LEFT_PANEL_WIDTH,LEFT_PANEL_HEIGHT));
}
}
