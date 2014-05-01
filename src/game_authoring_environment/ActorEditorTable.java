package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import objects.NonPlayer;
import controller.GAEController;

public class ActorEditorTable extends PanelTable{

	private static final String[] moveTypes = {"Immobile", "Regular", "Back and Forth", "Back and Forth With Vertical Speed"};
	private static final String[] shootTypes = {"None", "Slow Shoot", "Spread Shoot", "Quick Shoot", "Slow Shoot by Time", "Spread Shoot by Time"};
	private static final String[] dieTypes = {"Immortal", "Disappear", "Show Corpse"};

	private String name; 
	//private static final String[] collisionTypes = {"Explode", "Hitter Eliminate Victim"};

	private GAEController gController;

	public ActorEditorTable(GAEController controller) {
		super();
		gController = controller;
	}

	@Override
	public void init() {

		final JTextField tf = new JTextField(name);
		Object[] firstRow = {"Name", tf}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gController.modifyActorName(tf.getText());
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
					JTextField amplitude = new JTextField(10);
					JTextField Speed = new JTextField(10);
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
							gController.modifyActorRegMoveNoID(Double.valueOf(xSpeed.getText()), Double.valueOf(ySpeed.getText()));

						}

						break;
					}
					case "Immobile":
						gController.modifyActorImmobileNoID();
						break;
					case "Back and Forth":
						
						JTextField[] texts = {amplitude, Speed};
						String[] strings = {"Amplitude:", "Speed:"};
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							gController.modifyActorBackForthMoveNoID(Double.parseDouble(amplitude.getText()), Integer.parseInt(Speed.getText()));
						}
						break;
						
						//NEED TO FINISH THIS ::
					case "Back and Forth With Vertical Speed":{
						JTextField[] texts_ = {amplitude, Speed};
						String[] strings_ = {"Amplitude:", "Speed:"};
						myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);

						result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
					//		gController.modifyActorBackForthMoveNoID(Double.parseDouble(amplitude.getText()), Integer.parseInt(Speed.getText()));
						}
						break;
					}
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
					JTextField xSizeField = new JTextField(10);
					JTextField ySizeField = new JTextField(10);
					JTextField speedField = new JTextField(10);
					JTextField bulletsField = new JTextField(10);
					JTextField maxBullets = new JTextField(10);
					JTextField bulletColid = new JTextField(10);

					switch(str){

					case "Slow Shoot":{
						JTextField[] texts = {xSizeField, ySizeField, speedField, maxBullets,bulletColid};
						String[] strings = {"x size:", "y size:", "Speed:", "Max Bullets","Bullet ColID"};
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
							UIManager.put("FileChooser.openDialogTitleText", "Choose Bullet Image");
							SwingUtilities.updateComponentTreeUI(chooser);
							FileNameExtensionFilter filter = new FileNameExtensionFilter(
									"jpg", "gif","png","jpeg");
							chooser.setFileFilter(filter);
							int returnVal = chooser.showOpenDialog(getParent());
							if(returnVal == JFileChooser.APPROVE_OPTION) {
								String path = chooser.getSelectedFile().getPath();
								String name = chooser.getSelectedFile().getName();
								gController.uploadImage(100, 100, path);
								gController.modifyActorSlowShoot(name,  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), Integer.parseInt(speedField.getText()), Integer.parseInt(maxBullets.getText()));
							}
							else{
								gController.modifyActorSlowShoot("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()), Integer.parseInt(maxBullets.getText()));

							}
						}

						break;
					}
					case "Quick Shoot":
						JTextField[] texts_ = {xSizeField, ySizeField, speedField, bulletsField, maxBullets};
						String[] strings_ = {"x size:", "y size:", "Speed:", "Number of Bullets Per Shot", "Max Bullets","Bullet ColID"};
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {							
							JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
							UIManager.put("FileChooser.openDialogTitleText", "Choose Bullet Image");
							SwingUtilities.updateComponentTreeUI(chooser);
							FileNameExtensionFilter filter = new FileNameExtensionFilter(
									"jpg", "gif","png","jpeg");
							chooser.setFileFilter(filter);
							int returnVal = chooser.showOpenDialog(getParent());
							if(returnVal == JFileChooser.APPROVE_OPTION) {
								String path = chooser.getSelectedFile().getPath();
								String name = chooser.getSelectedFile().getName();
								gController.uploadImage(100, 100, path);
								gController. QuickShoot(name,  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()), Integer.parseInt(maxBullets.getText()) );
							}
							else{
								gController.modifyActorQuickShoot("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()), Integer.parseInt(maxBullets.getText()) );
							}
						}
						break;
						case "Spread Shoot":{
							JTextField[] texts1 = {xSizeField, ySizeField, speedField, bulletsField,bulletColid};
							String[] strings1 = {"x size:", "y size:", "Speed:", "Number of Bullets Per Shot","Bullet ColID"};
							JPanel myPanel1 = ViewFactory.createOptionInputPanel(texts1, strings1);
							result = JOptionPane.showConfirmDialog(null, myPanel1, 
									"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
								UIManager.put("FileChooser.openDialogTitleText", "Choose Bullet Image");
								SwingUtilities.updateComponentTreeUI(chooser);
								FileNameExtensionFilter filter = new FileNameExtensionFilter(
										"jpg", "gif","png","jpeg");
								chooser.setFileFilter(filter);
								int returnVal = chooser.showOpenDialog(getParent());
								if(returnVal == JFileChooser.APPROVE_OPTION) {
									String path = chooser.getSelectedFile().getPath();
									String name = chooser.getSelectedFile().getName();
									gController.uploadImage(100, 100, path);
							//		gController.modifyActorSpreadShootNoID(name,  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()) );
								}
								else{
								//	gController.modifyActorSpreadShootNoID("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()) );
								}
							}
							break;
						}
						case "Slow Shoot by Time":{
							
							break;
						}
						case "Spread Shoot by Time":{
							break;
						}
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
					switch(str){
					case "Immortal":
						gController.modifyPlayerImmortal();
						break;
					case "Show Corpse":

						//	gController.modifyActorShowCorpseNoID("bullet.png", 10, 10, 10);
						break;
					case "Regular Remove":
						gController.modifyActorRegRemoveNoID();
						break;
					default:
						break;
					}
				}
			}
		});		
		myTableModel.addRow(fourthRow);
		classMap.put(3,fourthRow[1]);
		
		
		final JTextField tf1 = new JTextField();
		Object[] fifthRow = {"Speed (X,Y)", tf1}; // each row should be in this format
		tf1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String input = tf1.getText().toString();
				String delim = ",";
				String[] list = input.split(delim);
				if(list.length == 2);{
					gController.modifyActorSpeedNoID(Double.parseDouble(list[0]), Double.parseDouble(list[1]));
				}
			}			
		});

		myTableModel.addRow(fifthRow); // actually adding to the table
		classMap.put(4,fifthRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)		

		final JTextField tf2 = new JTextField();
		Object[] sixthRow = {"Collision ID", tf2}; // each row should be in this format
		tf2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gController.modifyActorColIDNoID(Integer.parseInt(tf2.getText()));
			}

		});

		myTableModel.addRow(sixthRow); // actually adding to the table
		classMap.put(5,sixthRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)		
	
		final JCheckBox jb = new JCheckBox();
		Object[] seventhRow = {"Jump",jb};
		jb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					JTextField numberJumpsField = new JTextField(10);
					JTextField magnitudeField = new JTextField(10);
					JTextField[] texts_ = {magnitudeField, numberJumpsField};
					String[] strings_ = {"Height:", "Number of Jumps"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						gController.modifyActorJump(Double.parseDouble(magnitudeField.getText()), Integer.parseInt(numberJumpsField.getText()) );
					}
					else{
						jb.setSelected(false);
					}
				}
				else{
					int id = gController.getActorID();
					gController.modifyPlayerCanNotJump("canNotJump");
				}
			}
		});	

		myTableModel.addRow(seventhRow);
		classMap.put(6, seventhRow[1]);
	
	
	}


		@Override
		void updateTable() {
			NonPlayer actor = gController.getNonPlayer();


			makeTable();
			init();

		}

	}
