package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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


			final JTextField tf = new JTextField("test");
			Object[] firstRow = {"Name", tf}; // each row should be in this format
			tf.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(tf.getText());
					int id = gController.getActorID();
					gController.modifyActorName(id, tf.getText());
				}			
			});

			myTableModel.addRow(firstRow); // actually adding to the table
			classMap.put(0,firstRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)		

			JComboBox moveTypesBox = new JComboBox(moveTypes);
			Object[] secondRow = {"Movement", moveTypesBox};
			moveTypesBox.setSelectedIndex(0);
			moveTypesBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange() == ItemEvent.SELECTED){
						String str = arg0.getItem().toString();
						System.out.println("new selected item:"+arg0.getItem().toString());
						switch(str){
						case "Regular":
						{	

							JTextField xSpeed = new JTextField(10);
							JTextField ySpeed = new JTextField(10);
							JTextField[] texts = {xSpeed, ySpeed};
							String[] strings = {"x speed:", "y speed:"};
							JPanel myPanel = createOptionInputPanel(texts, strings);

							int result = JOptionPane.showConfirmDialog(null, myPanel, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								gController.modifyActorRegMoveNoID(Integer.parseInt(xSpeed.getText()), Integer.parseInt(ySpeed.getText()));

							}

							break;
						}
						case "Immobile":
							gController.modifyActorImmobileNoID();
							break;
						case "Cyclical":
							JTextField amplitude = new JTextField(10);
							JTextField Speed = new JTextField(10);
							JTextField[] texts = {amplitude, Speed};
							String[] strings = {"Amplitude:", "Speed:"};
							JPanel myPanel = createOptionInputPanel(texts, strings);

							int result = JOptionPane.showConfirmDialog(null, myPanel, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								gController.modifyActorBackForthMoveNoID(Double.parseDouble(amplitude.getText()), Integer.parseInt(Speed.getText()));
							}
								break;
						}
					}				
				}
			});
			myTableModel.addRow(secondRow); // actually adding to the table
			classMap.put(1,secondRow[1]);


			JComboBox shootTypesBox = new JComboBox(shootTypes);
			Object[] thirdRow = {"Shooting", shootTypesBox};
			shootTypesBox.setSelectedIndex(0);
			shootTypesBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange() == ItemEvent.SELECTED){
						String str = arg0.getItem().toString();
						System.out.println("new selected item:"+arg0.getItem().toString());
						JTextField xSizeField = new JTextField(10);
						JTextField ySizeField = new JTextField(10);
						JTextField speedField = new JTextField(10);
						switch(str){

						case "Slow Shoot":{
							JTextField[] texts = {xSizeField, ySizeField, speedField};
							String[] strings = {"x size:", "y size:", "Speed:"};
							JPanel myPanel = createOptionInputPanel(texts, strings);

							int result = JOptionPane.showConfirmDialog(null, myPanel, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								gController.modifyActorSlowShootNoID("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()));
							}

							break;
						}
						case "Quick Shoot":
							JTextField bulletsField = new JTextField(10);
							JTextField[] texts_ = {xSizeField, ySizeField, speedField, bulletsField};
							String[] strings_ = {"x size:", "y size:", "Speed:", "Number of Bullets Per Shot"};
							JPanel myPanel = createOptionInputPanel(texts_, strings_);

							int result = JOptionPane.showConfirmDialog(null, myPanel, 
									"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								gController.modifyActorQuickShootNoID("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()) );
							}
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

			JComboBox dieTypesBox = new JComboBox(dieTypes);
			Object[] fifthRow = {"Death Behavior", dieTypesBox};
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
			myTableModel.addRow(fifthRow);
			classMap.put(4,fifthRow[1]);

		}
		}

		@Override
		void updateTable() {
			
			
		}

}
