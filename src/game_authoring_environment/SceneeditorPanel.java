package game_authoring_environment;

import javax.swing.JComponent;

import controller.GAEController;

public class SceneeditorPanel extends Panel {
	
	private GAEController gController;

	public SceneeditorPanel(GAEController gController) {
		super(PanelType.SCENEEDITOR);
		this.gController = gController;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void construct() {
		

	}

	@Override
	protected void makeSubPanel() {
		// TODO Auto-generated method stub

	}

	@Override
	protected JComponent makeSubPanelItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
