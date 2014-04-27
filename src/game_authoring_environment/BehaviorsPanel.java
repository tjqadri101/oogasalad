package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.GAEController;

public class BehaviorsPanel extends Panel {

	private static final String BRICK_DEFAULT_IMAGE = "brick.png";
	private SubPanel mySubPanel;
	private GAEController gController;
	private JTable myTable; 

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
		JPanel outPanel = new JPanel();
		outPanel.setLayout(new BorderLayout());	
		
		JButton enterTileMode = ViewFactory.createJButton("Enter Tile Mode");
		enterTileMode.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				enterTileEditingMode();
			}
		});

		JButton exitTileMode = ViewFactory.createJButton("Exit Tile Mode");
		exitTileMode.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				exitTileEditingMode();
			}
		});

		outPanel.add(enterTileMode,BorderLayout.NORTH);
		outPanel.add(exitTileMode,BorderLayout.SOUTH);
		
		return outPanel;
	}
	
	private void enterTileEditingMode(){
		JPanel panel = new JPanel(new GridLayout(0, 1));
		JTextField tf = new JTextField();
		panel.add(tf);
		int result = JOptionPane.showConfirmDialog(null, panel, "Enter tile collision ID",
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	        	int colID = Integer.parseInt(tf.getText());
	        	gController.setDragTile(colID, BRICK_DEFAULT_IMAGE);
	            System.out.println(colID);
	        }
	}
	
	private void exitTileEditingMode(){
		
	}
	
	public JTable createTable(){
		myTable = new BehaviorTable(gController);
		return myTable;
	}
	

}
