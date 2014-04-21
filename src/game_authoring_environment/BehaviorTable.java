package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import controller.GAEController;

public class BehaviorTable extends PanelTable {

	private GAEController gController;
	private static final String[] collisionTypes = {"Explode", "HitterEliminateVictim", "PerishTogether", "StayOnTile"};
	public BehaviorTable(GAEController c) {
		gController = c;
	}

	@Override
	void updateTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void init() {

		final JTextField tf = new JTextField();
		Object[] firstRow = {"Collision ID", tf}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("new text:" + tf.getText());	
			}			
		});
		
		myTableModel.addRow(firstRow); // actually adding to the table
		classMap.put(0,firstRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)
		
		JComboBox collisionTypesBox = new JComboBox(collisionTypes);
		Object[] secondRow = {"Collision Type", collisionTypesBox};
		collisionTypesBox.setSelectedIndex(0);
		collisionTypesBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					System.out.println("new selected item:"+arg0.getItem().toString());
					// call the change method in GAEController here (change level;change scene etc)
				}				
			}
		});		
		myTableModel.addRow(secondRow);
		classMap.put(1,secondRow[1]);
		
		
		
	}

}
