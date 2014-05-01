package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GAEController;

public class BehaviorTable extends PanelTable {

	private GAEController gController;
	private static final String[] colIDs = {"1","2"};
	private static final String[] collisionTypes = {"Explode", "HitterEliminateVictim", "PerishTogether", "StayOnTile"};
	private String currentSelectedItem;
	private String currentImputID;
	
	
	public BehaviorTable(GAEController c) {
		gController = c;
		
	}

	@Override
	void updateTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void init() {
		
	/*	final JTextField tf = new JTextField();
		Object[] firstRow = {"Collision ID", tf}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("new text:" + tf.getText());	
				currentImputID = tf.getText();
			}			
		});*/
		JComboBox collisionTypesBox = new JComboBox(collisionTypes);
		JComboBox colIDsBox = new JComboBox(colIDs);
		Object[] firstRow = {"String", "String", "sample"};
		collisionTypesBox.setSelectedIndex(0);
		collisionTypesBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					// call the change method in GAEController here (change level;change scene etc)
					currentSelectedItem = arg0.getItem().toString();
				}				
			}
		});		
		myTableModel.addRow(firstRow); // actually adding to the table
		classMap.put(0,firstRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)
		/*
		
		myTableModel.addRow(secondRow);
		classMap.put(1,secondRow[1]);
	*/
		
		
	}
	
	public String getCurrentSelectedItem(){
		return currentSelectedItem;
	}
	
	public int getCurrentInputID(){
		return Integer.parseInt(currentImputID);
	}

}
