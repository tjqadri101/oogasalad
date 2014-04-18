package game_authoring_environment;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import controller.GAEController;

	public class FullView extends JSplitPane{


		private static LeftPanel lp;
		private static RightPanel rp;
		public FullView(GAEController gController){			
			setOrientation(HORIZONTAL_SPLIT);
			lp = new LeftPanel(gController);
			rp = new RightPanel(gController);
			setLeftComponent(lp);
			setRightComponent(rp);
		}
		
		public HashMap<String, JPanel> getMap(){
			return lp.setUpMap();
		}
		
		public AttributesPanel getAttributes(){
			return lp.getAttributes();
		}
		
		
	}