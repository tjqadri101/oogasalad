/**
 * @author Kat Krieger
 */
package game_authoring_environment;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import controller.GAEController;

public class GameEditorTable extends PanelTable {

	private GAEController gController;
	private static final String[] goalTypes = {"Position", "Time", "Collide"}; 
	
	public GameEditorTable(GAEController c) {
		gController = c;
	}

	@Override
	void updateTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void init() {
		JComboBox goalTypesBox = new JComboBox(goalTypes);
		Object[] firstRow = {"Death Behavior", goalTypesBox};
		goalTypesBox.setSelectedIndex(0);
		goalTypesBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					System.out.println("new selected item:"+arg0.getItem().toString());
					// call the change method in GAEController here (change level;change scene etc)
				}				
			}
		});		
		myTableModel.addRow(firstRow);
		classMap.put(0,firstRow[1]);
		
		
	}

}
