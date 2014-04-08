package game_authoring_environment;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.GAEController;

public class BehaviorsPanel extends Panel {

	private SubPanel mySubPanel;
	private GAEController gController;
	
	public BehaviorsPanel(GAEController gController) {
		super(PanelType.BEHAVIORS);
		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(mySubPanel),BorderLayout.NORTH);
		this.add(new JTextField(),BorderLayout.SOUTH);

	}

	@Override
	protected void makeSubPanel() {
		mySubPanel = (SubPanel) ViewFactory.buildPanel(PanelType.SUB,gController);
		mySubPanel.setSuperType(getType());
		mySubPanel.addItems(makeSubPanelItems());
		mySubPanel.construct();
		
	}

	@Override
	protected JComponent makeSubPanelItems() {
		return new JLabel("");
		
	}

}
