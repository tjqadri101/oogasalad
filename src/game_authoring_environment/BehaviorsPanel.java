package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		//this.add(createKeySetButton(),BorderLayout.SOUTH);
		this.add(new JScrollPane(createTable()), BorderLayout.CENTER);

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

			}
		});

		JButton exitTileMode = ViewFactory.createJButton("Exit Tile Mode");
		exitTileMode.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){

			}
		});

		outPanel.add(enterTileMode,BorderLayout.NORTH);
		outPanel.add(exitTileMode,BorderLayout.SOUTH);

		return outPanel;
	}

	

	public JTable createTable(){
		myTable = new BehaviorTable(gController);
		return myTable;
	}


}
