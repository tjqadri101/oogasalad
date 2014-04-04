package game_authoring_environment;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class RightPanel extends JSplitPane {

	public RightPanel(){
		setOrientation(VERTICAL_SPLIT);
		setTopComponent(new JPanel());
		setBottomComponent(new JPanel());
	}	
}
