package game_authoring_environment;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class ActoreditorPanel extends Panel {
	
	private SubPanel mySubPanel;
	private PanelType superType;
	private JComponent myComponent;
	
	public ActoreditorPanel() {
		super(PanelType.ACTOREDITOR);
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());		
		this.add(mySubPanel, BorderLayout.NORTH);
		TableRenderDemo t = new TableRenderDemo();
		this.add(t, BorderLayout.CENTER);
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
		JButton jb  = new JButton("test");
		return jb;
	}
	
	
	

}
