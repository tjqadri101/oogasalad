package game_authoring_environment;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import controller.GAEController;

	public class FullView extends JSplitPane{

		private static final int FULL_VIEW_HEIGHT = 768;
		private static final int FULL_VIEW_WIDTH = 1024;

		public FullView(GAEController gController){			
			setOrientation(HORIZONTAL_SPLIT);
			LeftPanel lp = new LeftPanel(gController);
			RightPanel rp = new RightPanel(gController);
			setLeftComponent(lp);
			setRightComponent(rp);
		}
		
		
		

}