package game_authoring_environment;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

	public class FullView extends JSplitPane{

		private static final int FULL_VIEW_HEIGHT = 768;
		private static final int FULL_VIEW_WIDTH = 1024;

		public FullView(){			
			setOrientation(HORIZONTAL_SPLIT);
			LeftPanel lp = new LeftPanel();
			RightPanel rp = new RightPanel();
			setLeftComponent(lp);
			setRightComponent(rp);
			setPreferredSize(new Dimension(FULL_VIEW_WIDTH,FULL_VIEW_HEIGHT));
		}
		
		
		

}