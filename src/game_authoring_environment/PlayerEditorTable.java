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
	private static final String[] shootTypes = {"Slow Shoot", "Quick Shoot", "Spread Shoot"};
	private static final String[] dieTypes = {"Immortal", "Remove Corpse"};
	private GAEController gController;
	private PlayereditorPanel parentPanel;
	private String playerName;
	private boolean playerExists;
	
	public PlayerEditorTable(GAEController c, PlayereditorPanel p, boolean playerExists2){
		super(playerExists2);
		parentPanel = p;
		gController = c;
		setPlayerExists(playerExists2);
		makeTable();
		init();

		
	}


	@Override
	public void init() {
		//player name
		final JTextField tf = new JTextField(playerName);
		Object[] firstRow = {"Name", tf}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerName = tf.getText().toString(); 
				gController.modifyPlayerName(playerName);
			}			
		});
		myTableModel.addRow(firstRow); // actually adding to the table
		classMap.put(0,firstRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)		

		final JComboBox shootTypesBox = new JComboBox(shootTypes);
		Object[] thirdRow = {"Shooting", shootTypesBox};
		shootTypesBox.setSelectedIndex(0);
		if(!playerExists){
			shootTypesBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange() == ItemEvent.SELECTED){
						JOptionPane.showMessageDialog(null, "Create the Player First, then add this feature", "alert", JOptionPane.ERROR_MESSAGE);
						shootTypesBox.setSelectedIndex(0);
					}
				}
			});
		}
		else{
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
									gController.modifyPlayerSlowShoot(name,  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), Integer.parseInt(speedField.getText()), Integer.parseInt(maxBullets.getText()));
								}
								else{
									gController.modifyPlayerSlowShoot("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), Integer.parseInt(speedField.getText()), Integer.parseInt(maxBullets.getText()));

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
									gController.modifyPlayerQuickShoot(name,  Integer.parseInt(xSizeField.getText()), 
											Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), Integer.parseInt(speedField.getText()),
											Integer.parseInt(bulletsField.getText()), Integer.parseInt(maxBullets.getText()) );
								}
								else{
									gController.modifyPlayerQuickShoot("bullet.png",  Integer.parseInt(xSizeField.getText()), 
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
										gController.modifyPlayerSpreadShoot(name,  Integer.parseInt(xSizeField.getText()), 
												Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()), Integer.parseInt(speedField.getText()),
												Integer.parseInt(bulletsField.getText()), Integer.parseInt(maxBullets.getText()) );
									}
									else{
										gController.modifyPlayerSpreadShoot("bullet.png",  Integer.parseInt(xSizeField.getText()), 
												Integer.parseInt(ySizeField.getText()), Integer.parseInt(bulletColid.getText()),
												Integer.parseInt(speedField.getText()),Integer.parseInt(bulletsField.getText()), 
												Integer.parseInt(maxBullets.getText()) );
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
		}	
		myTableModel.addRow(thirdRow);
		classMap.put(1,thirdRow[1]);
		
		
		//DEATH DROPDOWN:
		final JComboBox dieTypesBox = new JComboBox(dieTypes);
		Object[] fourthRow = {"Death Behavior", dieTypesBox};
		dieTypesBox.setSelectedIndex(0);
		if(!playerExists){
			dieTypesBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange() == ItemEvent.SELECTED){
						JOptionPane.showMessageDialog(null, "Create the Player First, then add this feature", "alert", JOptionPane.ERROR_MESSAGE);
						dieTypesBox.setSelectedIndex(0);
					}
				}
			});
		}
		else{
			dieTypesBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange() == ItemEvent.SELECTED){
						String str = arg0.getItem().toString();
						switch(str){
						case "Immortal":
							gController.modifyPlayerImmortal();
							break;
						case "Remove Corpse":
							gController.modifyPlayerRegRemove();
							break;
						default:
							break;
						}
					}
				}
			});		
		}
		myTableModel.addRow(fourthRow);
		classMap.put(2,fourthRow[1]);
		//JUMP CHOICES
		final JCheckBox jb = new JCheckBox();
		Object[] fifthRow = {"Jump",jb};
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
						gController.modifyPlayerJumpBehavior(Double.parseDouble(magnitudeField.getText()), Integer.parseInt(numberJumpsField.getText()) );
					}
					else{
						jb.setSelected(false);
					}
				}
				else{
					int id = gController.getActorID();
					gController.modifyPlayerCanNotJump();
				}
			}
		});	

		myTableModel.addRow(fifthRow);
		classMap.put(3,fifthRow[1]);

		final JTextField tf1 = new JTextField();
		Object[] sixthRow = {"Speed (X,Y)", tf1}; // each row should be in this format
		tf1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String input = tf1.getText().toString();
				String delim = ",";
				String[] list = input.split(delim);
				if(list.length == 2);{
					gController.modifyPlayerSpeed(Double.parseDouble(list[0]), Double.parseDouble(list[1]));
				}
			}			
		});

		myTableModel.addRow(sixthRow); // actually adding to the table
		classMap.put(4,sixthRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)		

		final JCheckBox cb = new JCheckBox();
		Object[] seventhRow = {"Set AutoMovement",cb};
		cb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					JTextField xSpeed = new JTextField(10);
					JTextField ySpeed = new JTextField(10);
					JTextField[] texts_ = {xSpeed, ySpeed};
					String[] strings_ = {"X Speed:", "Y Speed"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						gController.modifyPlayerSpeed(Double.parseDouble(xSpeed.getText()), Double.parseDouble(ySpeed.getText()) );
					}
					else{
						cb.setSelected(false);
					}
				}
				else{
					int id = gController.getActorID();
					gController.modifyPlayerSpeed(0,0);
				}
			}
		});	

		myTableModel.addRow(seventhRow);
		classMap.put(5,seventhRow[1]);
		
		final JTextField tf11 = new JTextField();
		Object[] eighthRow = {"Initial Blood", tf11}; 
		tf11.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gController.modifyPlayerInitBlood(Integer.parseInt(tf11.getText()));
			}			
		});

		myTableModel.addRow(eighthRow); // actually adding to the table
		classMap.put(6,eighthRow[1]);
		
		final JTextField tf7 = new JTextField();
		Object[] third_Row = {"Initial Lives", tf7}; // each row should be in this format
		tf7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gController.modifyLifeManagerInitLives(Integer.parseInt(tf7.getText()));
			}			
		});
		myTableModel.addRow(third_Row);
		classMap.put(7,third_Row[1]);
	}

	
	@Override
	void updateTable() {
		

	}
	
	public String getName(){
		return playerName;
	}
	
	public void setPlayerExists(boolean k){
		playerExists = k;
	}

}
