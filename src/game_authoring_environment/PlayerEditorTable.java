package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import saladConstants.SaladConstants;
import controller.GAEController;


//private static final String[] collisionTypes = {"Explode", "Hitter Eliminate Victim"};



public class PlayerEditorTable extends PanelTable {
	
//	private static final String[] moveTypes = {};
	private static final String[] shootTypes = {"None", "Slow Shoot", "Quick Shoot"};
	private static final String[] dieTypes = {"Immortal", "Remove Corpse", "Show Corpse"};
	private GAEController gController;
	private PlayereditorPanel parentPanel;

	public PlayerEditorTable(GAEController c, PlayereditorPanel p){
		super();
		parentPanel = p;
		gController = c;
	}


	@Override
	public void init() {

//player name
		final JTextField tf = new JTextField("test");
		Object[] firstRow = {"Name", tf}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tf.getText());
				int id = gController.getActorID();
			}			
		});

		myTableModel.addRow(firstRow); // actually adding to the table
		classMap.put(0,firstRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)		

//moveTypes
		/*JComboBox moveTypesBox = new JComboBox(moveTypes);
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
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							gController.modifyActorRegMoveNoID(Double.parseDouble(xSpeed.getText()), Double.parseDouble(ySpeed.getText()));

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
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

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
		classMap.put(1,secondRow[1]);*/


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
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							gController.modifyPlayerSlowShootNoID("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()));
						}

						break;
					}
					case "Quick Shoot":
						JTextField bulletsField = new JTextField(10);
						JTextField[] texts_ = {xSizeField, ySizeField, speedField, bulletsField};
						String[] strings_ = {"x size:", "y size:", "Speed:", "Number of Bullets Per Shot"};
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							gController.modifyPlayerQuickShootNoID("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Double.parseDouble(speedField.getText()),Integer.parseInt(bulletsField.getText()) );
						}
						break;
					case "None":
						gController.modifyPlayerSlowShootNoID("bullet.png", 0, 0, 0, 0);
					default:
						break;
					}
				}				
			}
		});		
		myTableModel.addRow(thirdRow);
		classMap.put(1,thirdRow[1]);

//DEATH DROPDOWN:
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
						JTextField xSizeField = new JTextField(10);
						JTextField ySizeField = new JTextField(10);
						JTextField speedField = new JTextField(10);
						JTextField bulletsField = new JTextField(10);
						JTextField[] texts_ = {xSizeField, ySizeField, speedField, bulletsField};
						String[] strings_ = {"x size:", "y size:", "Speed:", "Number of Bullets Per Shot"};
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);
						UIManager.put("OptionPane.okButtonText", "Now Choose Actor Image");  
						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {

							try{
								JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
								UIManager.put("FileChooser.openDialogTitleText", "Choose Corpse Image");
								SwingUtilities.updateComponentTreeUI(chooser);
								FileNameExtensionFilter filter = new FileNameExtensionFilter(
										"jpg", "gif","png","jpeg");
								chooser.setFileFilter(filter);
								int returnVal = chooser.showOpenDialog(getParent());
								if(returnVal == JFileChooser.APPROVE_OPTION) {
									String path = chooser.getSelectedFile().getPath();
									String name = chooser.getSelectedFile().getName();
									gController.uploadImage(10,10, path);
									//	gController.modifyPlayerShowCorpseNoID(path,  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()) );

								}			
							}catch(Exception e){
							}
						}
						break;

					case "Remove Corpse":
						gController.modifyPlayerRegRemoveNoID();
						break;
					default:
						break;
					}
				}
			}
		});		
		myTableModel.addRow(fourthRow);
		classMap.put(2,fourthRow[1]);

//JUMP CHOICES
		final JCheckBox jb = new JCheckBox();
		Object[] fifthRow = {"Jump",jb};
		jb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					System.out.println("now checked:"+true);
					JTextField numberJumpsField = new JTextField(10);
					JTextField magnitudeField = new JTextField(10);
					JTextField[] texts_ = {magnitudeField, numberJumpsField};
					String[] strings_ = {"Height:", "Number of Jumps"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						gController.modifyPlayerJumpBehaviorNoID(Double.parseDouble(magnitudeField.getText()), Double.parseDouble(numberJumpsField.getText()) );
					}
					else{
						jb.setSelected(false);
					}
				}
				else{
					System.out.println("now checked:"+false);
					gController.modifyPlayerJumpBehaviorNoID(0,0);
				}
			}
		});	


		myTableModel.addRow(fifthRow);
		classMap.put(3,fifthRow[1]);
		//player name
				final JTextField tf1 = new JTextField();
				Object[] sixthRow = {"Speed (X,Y)", tf1}; // each row should be in this format
				tf1.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.out.println(tf1.getText());
						String input = tf1.getText().toString();
						String delim = ",";
						String[] list = input.split(delim);
						if(list.length == 2);{
							gController.modifyPlayerSpeedNoID(Double.parseDouble(list[0]), Double.parseDouble(list[1]));
						}
				}			
				});

				myTableModel.addRow(sixthRow); // actually adding to the table
				classMap.put(4,sixthRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)		
				//player name

	}

	@Override
	void updateTable() {


	}

}
