package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.GAEController;


//private static final String[] collisionTypes = {"Explode", "Hitter Eliminate Victim"};



public class PlayerEditorTable extends PanelTable {

	private static final String[] shootTypes = {"None", "Slow Shoot", "Quick Shoot"};
	private static final String[] dieTypes = {"Immortal", /*"Disappear",*/ "Show Corpse"};
	private GAEController gController;
	
	public PlayerEditorTable(GAEController c){
		super();
		gController = c;
	}
	

		@Override
		public void init() {

			final JTextField t1 = new JTextField("test");
			Object[] firstRow1 = {"Name", t1}; // each row should be in this format
			t1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//logic needed
					gController.createPlayer(0, "actorsIcon.png", 60, 60, 10, 60, t1.getText(), 1, 1);
				}			
			});
			
			myTableModel.addRow(firstRow1); // actually adding to the table
			classMap.put(0,firstRow1[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)
			

			JComboBox shootTypesBox = new JComboBox(shootTypes);
			Object[] thirdRow = {"Shoot Behavior", shootTypesBox};
			shootTypesBox.setSelectedIndex(0);
			shootTypesBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange() == ItemEvent.SELECTED){
						String str = arg0.getItem().toString();
						System.out.println("new selected item:"+arg0.getItem().toString());
						switch(str){
						case "Slow Shoot":
							gController.modifyPlayerSlowShootNoID("bullet.png", 1, 1, 1, 1);
							break;
						case "Quick Shoot":
							gController.modifyPlayerQuickShootNoID("bullet.png", 2, 2, 2, 2, 10);
							break;
						default:
							break;
						}
					}				
				}
			});		
			myTableModel.addRow(thirdRow);
			classMap.put(1,thirdRow[1]);
			
			
			JComboBox dieTypesBox = new JComboBox(dieTypes);
			Object[] fourthRow = {"Death Behavior", dieTypesBox};
			dieTypesBox.setSelectedIndex(0);
			dieTypesBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange() == ItemEvent.SELECTED){
						String str = arg0.getItem().toString();
						System.out.println("new selected item:"+arg0.getItem().toString());
						switch(str){
						case "Immortal":
							gController.modifyPlayerImmortalNoID();
							break;
						case "Show Corpse":
							gController.modifyPlayerShowCorpseNoID("bullet.png", 10, 10, 10);
							break;
						default:
							break;
						}
					}
				}
			});		
			myTableModel.addRow(fourthRow);
			classMap.put(2,fourthRow[1]);
			
			
		/*	JCheckBox jb = new JCheckBox();
			Object[] fifthRow = {"TestBoo", jb};
			jb.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange() == ItemEvent.SELECTED){
						System.out.println("now checked:"+true);
						// call the change method in GAEController here
					}
					else{
						System.out.println("now checked:"+false);
						// call the change method in GAEController here
					}
				}
			});	
			
			myTableModel.addRow(fifthRow);
			classMap.put(4,fifthRow[1]);
			*/
		}

		@Override
		void updateTable() {
			
			
		}

}
