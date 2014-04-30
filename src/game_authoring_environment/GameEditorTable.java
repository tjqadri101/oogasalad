/**
 * @author Kat Krieger
 */
package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GAEController;

public class GameEditorTable extends PanelTable {

	private GAEController gController;
	private static final String[] gameStates = {"Title", "GameOver", "LifeLost", "LevelDone", "StartGame"}; 

	public GameEditorTable(GAEController c) {
		gController = c;
	}

	@Override
	void updateTable() {
		// TODO Auto-generated method stub

	}

	@Override
	void init() {
		final JTextField name = new JTextField();
		Object[] zeroRow = {"Name: ", name}; // each row should be in this format
		name.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(name.getText());
				gController.modifyGameName(name.getText().toString());
			}			
		});
		myTableModel.addRow(zeroRow);
		classMap.put(0,zeroRow[1]);
		
		
		JComboBox gameStateBox = new JComboBox(gameStates);
		Object[] firstRow = {"Transition Image", gameStateBox};
		gameStateBox.setSelectedIndex(0);
		gameStateBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					String str = arg0.getItem().toString();
					System.out.println("new selected item:"+arg0.getItem().toString());

					JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
					UIManager.put("FileChooser.openDialogTitleText", "Choose Transition Image");
					SwingUtilities.updateComponentTreeUI(chooser);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"jpg", "gif","png","jpeg");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(getParent());
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						String path = chooser.getSelectedFile().getPath();
						String name = chooser.getSelectedFile().getName();
						gController.uploadImage(100, 100, path);
						gController.modifyTransitionStateBackground(arg0.getItem().toString(), name);

					}			
				}

			}
		});		
		myTableModel.addRow(firstRow);
		classMap.put(1,firstRow[1]);

		final JTextField tf = new JTextField();
		Object[] secondRow = {"Initial Score", tf}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tf.getText());
				gController.modifyScoreManagerInitScore(Integer.parseInt(tf.getText()));
			}			
		});
		myTableModel.addRow(secondRow);
		classMap.put(2,secondRow[1]);

		//GameOver, LifeLost, LevelDone,
		final JTextField tf1 = new JTextField();
		Object[] thirdRow = {"Game Over Frame Length", tf1}; // each row should be in this format
		tf1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tf1.getText());
				gController.modifyTransitionStateFrame("GameOver", Integer.parseInt(tf1.getText().toString()));
			}			
		});
		myTableModel.addRow(thirdRow);
		classMap.put(3,thirdRow[1]);

		final JTextField tf2 = new JTextField();
		Object[] fourthRow = {"Life Lost Frame Length", tf2}; // each row should be in this format
		tf2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tf2.getText());
				gController.modifyTransitionStateFrame("LifeLost", Integer.parseInt(tf2.getText().toString()));
			}			
		});
		myTableModel.addRow(fourthRow);
		classMap.put(4,fourthRow[1]);

		final JTextField tf3 = new JTextField();
		Object[] fifthRow = {"Level Done Frame Length", tf3}; // each row should be in this format
		tf3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tf3.getText());
				gController.modifyTransitionStateFrame("LevelDone", Integer.parseInt(tf3.getText().toString()));
			}			
		});
		myTableModel.addRow(fifthRow);
		classMap.put(5,fifthRow[1]);

		//change message box
		JComboBox gameStateMessages = new JComboBox(gameStates);
		Object[] sixthrow = {"Transition Message", gameStateMessages};
		gameStateMessages.setSelectedIndex(0);
		gameStateMessages.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					String str = arg0.getItem().toString();
					JTextField Message = new JTextField(10);
					JTextField xPosition = new JTextField(10);
					JTextField yPosition = new JTextField(10);
					JTextField[] texts_ = {Message, xPosition, yPosition };
					String[] strings_ = {"Message:", "X Position:", "Y Position:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);
					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {

						String message = Message.getText().toString();
						int xpos = Integer.parseInt(xPosition.getText().toString());
						int ypos = Integer.parseInt(xPosition.getText().toString());
						gController.modifyTransitionStateMessage(arg0.getItem().toString(), message, xpos, ypos);

					}			
				}

			}
		});		
		myTableModel.addRow(sixthrow);
		classMap.put(6,sixthrow[1]);

		JComboBox gameStateDisplayImage = new JComboBox(gameStates);
		Object[] seventhRow = {"Transition Image", gameStateDisplayImage};
		gameStateDisplayImage.setSelectedIndex(0);
		gameStateDisplayImage.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					JTextField xPosition = new JTextField(10);
					JTextField yPosition = new JTextField(10);
					JTextField[] texts_ = { xPosition, yPosition };
					String[] strings_ = {"X Position:", "Y Position:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts_, strings_);
					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						String str = arg0.getItem().toString();
						System.out.println("new selected item:"+arg0.getItem().toString());

						JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
						UIManager.put("FileChooser.openDialogTitleText", "Choose Transition Image");
						SwingUtilities.updateComponentTreeUI(chooser);
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"jpg", "gif","png","jpeg");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(getParent());
						if(returnVal == JFileChooser.APPROVE_OPTION) {
							String path = chooser.getSelectedFile().getPath();
							String name = chooser.getSelectedFile().getName();
							gController.uploadImage(100, 100, path);
							gController.modifyTransitionStateImage(arg0.getItem().toString(), name, Integer.parseInt(xPosition.getText().toString()), Integer.parseInt(xPosition.getText().toString()));

						}			

					}
				}
			}
				
			});	
		myTableModel.addRow(seventhRow);
		classMap.put(7,seventhRow[1]);
		
		final JTextField tf4 = new JTextField();
		Object[] eighthRow = {"Gravity Magnitude", tf4}; // each row should be in this format
		tf4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tf4.getText());
				gController.modifyGravityMagnitude(Double.parseDouble(tf4.getText()));
			}			
		});
		myTableModel.addRow(eighthRow);
		classMap.put(8,eighthRow[1]);
		
		
		JCheckBox cb5= new JCheckBox();
		Object[] ninthRow = {"Restore Life At End of Level", tf4}; // each row should be in this format
		cb5.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
				System.out.println(tf4.getText());
				gController.modifyLifeManagerRestoreLife(true);
				}
				else{
					gController.modifyLifeManagerRestoreLife(false);
				}
			}			
		});
		myTableModel.addRow(ninthRow);
		classMap.put(9,ninthRow[1]);
		
	
		}
	
		
	}


