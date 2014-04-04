package game_authoring_environment;

import javax.swing.JSplitPane;

	public class FullView extends JSplitPane{

		public FullView(){
			setOrientation(HORIZONTAL_SPLIT);
			LeftPanel lp = new LeftPanel();
			RightPanel rp = new RightPanel();
			setLeftComponent(lp);
			setRightComponent(rp);
		}
		

	}


