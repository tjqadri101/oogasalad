package game_authoring_environment;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class ActoreditorPanel extends Panel {
	
	private PanelType superType;
	private JComponent myComponent;
	private ViewFactory vf;
	
	public ActoreditorPanel() {
		super(PanelType.ACTOREDITOR);
	}

	@Override
	protected void construct() {
		makeSubPanel();
		this.setLayout(new BorderLayout());		
		this.add(new JLabel(superType.toString()),BorderLayout.WEST);
		this.add(myComponent,BorderLayout.EAST);

	}

	@Override
	protected void makeSubPanel() {
		
		//myComponent.add(vf.createFileChooser());
		
	}

	@Override
	protected JComponent makeSubPanelItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
