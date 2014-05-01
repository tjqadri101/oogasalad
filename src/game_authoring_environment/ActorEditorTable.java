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
	private static final String[] dieTypes = {"Immortal", "Disappear"};

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
					JTextField frequency = new JTextField(10);
					JTextField verticalSpeed = new JTextField(10);
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
						
						JTextField[] texts = {amplitude, frequency};
						String[] strings = {"Amplitude:", "Frequency:"};
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							gController.modifyActorBackForthMoveNoID(Double.parseDouble(amplitude.getText()), Integer.parseInt(frequency.getText()));
						}
						break;
						
						//NEED TO FINISH THIS ::
					case "Back and Forth With Vertical Speed":{
						JTextField[] texts2 = {amplitude, frequency, verticalSpeed};
						String[] strings2 = {"Amplitude:", "Frequency:", "VerticalSpeed:"};
						myPanel = ViewFactory.createOptionInputPanel(texts2, strings2);

						result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							gController.modifyActorBackForthVerticalMove(Double.parseDouble(amplitude.getText()), 
																	Integer.parseInt(frequency.getText()), 
																	Integer.parseInt(verticalSpeed.getText()));
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
					JTextField timeDelay = new JTextField(10);
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
							JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
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
								gController.modifyActorSlowShoot("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), Integer.parseInt(speedField.getText()), Integer.parseInt(maxBullets.getText()));

							}
						}

						break;
					}
					case "Quick Shoot":
						JTextField[] texts_ = {xSizeField, ySizeField, speedField, bulletsField, maxBullets, bulletColid};
						String[] strings_ = {"x size:", "y size:", "Speed:", "Number of Bullets Per Shot", "Max Bullets","Bullet ColID"};
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {							
							JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
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
								gController.modifyActorQuickShoot(name,  Integer.parseInt(xSizeField.getText()), 
										Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), Integer.parseInt(speedField.getText()),
										Integer.parseInt(bulletsField.getText()), Integer.parseInt(maxBullets.getText()) );
							}
							else{
								gController.modifyActorQuickShoot("bullet.png",  Integer.parseInt(xSizeField.getText()), 
										Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()),
										Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()), 
										Integer.parseInt(maxBullets.getText()) );
							}
						}
						break;
						case "Spread Shoot":{
							JTextField[] texts_2 = {xSizeField, ySizeField, speedField, bulletsField, maxBullets, bulletColid};
							String[] strings_2 = {"x size:", "y size:", "Speed:", "Number of Bullets Per Shot", "Max Bullets","Bullet ColID"};
							JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts_2, strings_2);

							int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result2 == JOptionPane.OK_OPTION) {							
								JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
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
									gController.modifyActorSpreadShoot(name,  Integer.parseInt(xSizeField.getText()), 
											Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), Integer.parseInt(speedField.getText()),
											Integer.parseInt(bulletsField.getText()), Integer.parseInt(maxBullets.getText()) );
								}
								else{
									gController.modifyActorSpreadShoot("bullet.png",  Integer.parseInt(xSizeField.getText()), 
											Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()),
											Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()), 
											Integer.parseInt(maxBullets.getText()) );
								}
							}
							break;
						}
						case "Slow Shoot by Time":{
							JTextField[] texts3 = {xSizeField, ySizeField, speedField, maxBullets,bulletColid, timeDelay};
							String[] strings3 = {"x size:", "y size:", "Speed:", "Max Bullets","Bullet ColID", "Shoot Delay"};
							JPanel myPanel3 = ViewFactory.createOptionInputPanel(texts3, strings3);

							int result3 = JOptionPane.showConfirmDialog(null, myPanel3, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result3 == JOptionPane.OK_OPTION) {
								JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
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
									gController.modifyActorSlowShootByTime(name,Integer.parseInt(xSizeField.getText()),
											Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()),
											Integer.parseInt(speedField.getText()), Integer.parseInt(timeDelay.getText()), 
											Integer.parseInt(maxBullets.getText()));
									
								}
								else{
									gController.modifyActorSlowShootByTime("bullet.png",Integer.parseInt(xSizeField.getText()),
											Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()),
											Integer.parseInt(speedField.getText()), Integer.parseInt(timeDelay.getText()), 
											Integer.parseInt(maxBullets.getText()));
								}
							}
							break;
						}
						case "Spread Shoot by Time":{
							JTextField[] texts_4 = {xSizeField, ySizeField, speedField, bulletsField, maxBullets, bulletColid, timeDelay};
							String[] strings_4 = {"x size:", "y size:", "Speed:", "Number of Bullets Per Shot", "Max Bullets","Bullet ColID", "Shoot Delay"};
							JPanel myPanel4 = ViewFactory.createOptionInputPanel(texts_4, strings_4);

							int result4 = JOptionPane.showConfirmDialog(null, myPanel4, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result4 == JOptionPane.OK_OPTION) {							
								JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
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
									gController.modifyActorSpreadShootByTime(name,  Integer.parseInt(xSizeField.getText()), 
											Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), 
											Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()), 
											Integer.parseInt(timeDelay.getText()), Integer.parseInt(maxBullets.getText()) );
								}
								else{
									gController.modifyActorSpreadShootByTime("bullet.png",  Integer.parseInt(xSizeField.getText()), 
											Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), 
											Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()), 
											Integer.parseInt(timeDelay.getText()), Integer.parseInt(maxBullets.getText()) );
								}
							}
							
							
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
						gController.modifyActorImmortalNoID();
						break;
					case "Disappear":
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
					gController.modifyActorCanNotJump();
				}
			}
		});	

		myTableModel.addRow(seventhRow);
		classMap.put(6, seventhRow[1]);
	
		final JTextField tf11 = new JTextField();
		Object[] eighthRow = {"Initial Blood", tf11}; 
		tf11.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gController.modifyActorInitBlood(Integer.parseInt(tf11.getText()));
			}			
		});

		myTableModel.addRow(eighthRow); // actually adding to the table
		classMap.put(7,eighthRow[1]);
		
	
	
	}
	
	
		@Override
		void updateTable() {
			NonPlayer actor = gController.getNonPlayer();


			makeTable();
			init();

		}

	}
