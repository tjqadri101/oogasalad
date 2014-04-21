package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import controller.GAEController;

public class ActorEditorTable extends PanelTable{

	private static final String[] moveTypes = {"Immobile", "Regular", /*"Cyclical"*/};
	private static final String[] shootTypes = {"None", "Slow Shoot", "Quick Shoot"};
	private static final String[] dieTypes = {"Immortal", /*"Disappear",*/ "Show Corpse"};
	//private static final String[] collisionTypes = {"Explode", "Hitter Eliminate Victim"};
	
	private GAEController gController;
	
	public ActorEditorTable(GAEController controller) {
		super();
		gController = controller;
	}
	
	@Override
	public void init() {

		final JTextField tf = new JTextField("test");
		Object[] firstRow = {"Name", tf}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("new text:" + tf.getText());	
			}			
		});
		
		myTableModel.addRow(firstRow); // actually adding to the table
		classMap.put(0,firstRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)
		
		
		JComboBox moveTypesBox = new JComboBox(moveTypes);
		Object[] secondRow = {"MoveBehavior", moveTypesBox};
		moveTypesBox.setSelectedIndex(0);
		moveTypesBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					String str = arg0.getItem().toString();
					System.out.println("new selected item:"+arg0.getItem().toString());
					switch(str){
					case "Regular":
						gController.modifyActorRegMoveNoID(5, 5);
						break;
					case "Immobile":
						gController.modifyActorImmobileNoID();
						break;
					}
				}				
			}
		});
		myTableModel.addRow(secondRow); // actually adding to the table
		classMap.put(1,secondRow[1]);
		
		
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
						gController.modifyActorSlowShootNoID("bullet.png", 1, 1, 1, 1);
						break;
					case "Quick Shoot":
						gController.modifyActorQuickShootNoID("bullet.png", 2, 2, 2, 2, 10);
						break;
					default:
						break;
					}
				}				
			}
		});		
		myTableModel.addRow(thirdRow);
		classMap.put(2,thirdRow[1]);
		
		
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
						gController.modifyActorShowCorpseNoID("bullet.png", 10, 10, 10);
						break;
					default:
						break;
					}
				}
			}
		});		
		myTableModel.addRow(fourthRow);
		classMap.put(3,fourthRow[1]);
		
		
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
