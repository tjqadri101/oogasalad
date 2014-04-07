package game_authoring_environment;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import controller.GAEController;

public class ActoreditorPanel extends Panel {
	
	private SubPanel mySubPanel;
	private PanelType superType;
	private JComponent myComponent;
	private GAEController gController;
	
	public ActoreditorPanel(GAEController gController) {
		super(PanelType.ACTOREDITOR);
		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());		
		this.add(mySubPanel, BorderLayout.NORTH);
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
		JButton jb  = new JButton("test");
		return jb;
	}

}
