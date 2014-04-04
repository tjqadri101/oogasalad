package game_authoring_environment;

import javax.swing.JSplitPane;


public class LeftPanel extends JSplitPane {
	
	public LeftPanel(){

	setOrientation(VERTICAL_SPLIT);
	Library libraryPane = new Library();
	Attributes attributesPane = new Attributes();
	setTopComponent(libraryPane);
	setBottomComponent(attributesPane);
}
}
