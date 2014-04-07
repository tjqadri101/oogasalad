package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import controller.GAEController;

public class SubPanel extends Panel {
	
	private PanelType superType;
	private JComponent myComponent;

	public SubPanel(GAEController gController) {
		super(PanelType.SUB);
		this.setBackground(Color.LIGHT_GRAY);	
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());		
		this.add(new JLabel(superType.toString()),BorderLayout.WEST);
		this.add(myComponent,BorderLayout.EAST);

	}

	@Override
	protected void makeSubPanel() {
		//do nothing

	}

	public void setSuperType(PanelType parent){
		superType = parent;
	}
	
	public void addItems(JComponent inComp){
		myComponent = inComp;
	}

	@Override
	protected JComponent makeSubPanelItems() {
		// do nothing
		return null;
	}
	
}
