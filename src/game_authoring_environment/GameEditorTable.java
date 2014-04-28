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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GAEController;

public class GameEditorTable extends PanelTable {

	private GAEController gController;
	private static final String[] gameStates = {"Title", "GameOver", "LifeLost", "LevelDone", "StartGame", "StartLevel", "HighScore"}; 
	
	public GameEditorTable(GAEController c) {
		gController = c;
	}

	@Override
	void updateTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void init() {
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
					UIManager.put("FileChooser.openDialogTitleText", "Choose Corpse Image");
					SwingUtilities.updateComponentTreeUI(chooser);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"jpg", "gif","png","jpeg");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(getParent());
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						String path = chooser.getSelectedFile().getPath();
						String name = chooser.getSelectedFile().getName();
						gController.modifyTransitionStateBackground(arg0.getItem().toString(), path);

					}			
				}
				
			}
		});		
		myTableModel.addRow(firstRow);
		classMap.put(0,firstRow[1]);
		
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
		classMap.put(1,secondRow[1]);
		
		
		
	}

}
