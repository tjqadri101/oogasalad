package game_authoring_environment;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BehaviorsPanel extends Panel {

	private SubPanel mySubPanel;
	
	public BehaviorsPanel() {
		super(PanelType.BEHAVIORS);
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());
		this.add(mySubPanel,BorderLayout.NORTH);
		this.add(new JTextField(),BorderLayout.SOUTH);

	}

	@Override
	protected void makeSubPanel() {
		mySubPanel = (SubPanel) ViewFactory.buildPanel(PanelType.SUB);
		mySubPanel.setSuperType(getType());
		mySubPanel.addItems(makeSubPanelItems());
		mySubPanel.construct();
		
	}

	@Override
	protected JComponent makeSubPanelItems() {
		return new JLabel("");
		
	}

}
