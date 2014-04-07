package game_authoring_environment;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;

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
	//	this.add(ViewFactory.createJFileChooser(), BorderLayout.CENTER);
		this.add(makeTable(), BorderLayout.SOUTH);
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
	
	public JComponent makeTable(){
		 
		 String[] columnNames = {"Category",
				  "Value",
				  "Type"};
				
				Object[][] data = {
				{"Name", new String(),
				"text"},
				{"Time", new Integer(0),
				"real"},
				{"Position X", new Integer(0),
				"real"},
				{"Position Y", new Integer(0),
				"real"},
				
				//{"Actor Tags", new String(), "text"}
				};
		JComponent k = ViewFactory.createScrollingTable(columnNames, data);
				return k;
	}
	

}
