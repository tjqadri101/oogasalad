package game_authoring_environment;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;



import javax.swing.JTextField;

import controller.GAEController;

public class SceneEditorTable extends PanelTable {

	private GAEController gController;
	private List<Integer> levelExist = new ArrayList<Integer>();
	private static final String[] level = {"1", "2", "3","4","5","6","7","8","9","10"}; 
	
	public SceneEditorTable(GAEController c) {
		gController = c;
		levelExist.add(1);
	}

	@Override
	void updateTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void init() {
		JComboBox goalTypesBox = new JComboBox(level);
		Object[] firstRow = {"Level", goalTypesBox};
		goalTypesBox.setSelectedIndex(0);
		goalTypesBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					setLevelForScene(Integer.parseInt(arg0.getItem().toString()));
				}				
			}
			
		});		
		myTableModel.addRow(firstRow);
		classMap.put(0,firstRow[1]);
		
		final JTextField tf = new JTextField();
		Object[] secondRow = {"Set Score at Scene End", tf}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gController.modifyScoreManagerTransitionScore(Integer.parseInt(tf.getText().toString()));
			}			
		});
		myTableModel.addRow(secondRow);
		classMap.put(1,secondRow[1]);
		
	}
	
	private void setLevelForScene(int newLevel) {
		int oldLevelID = gController.getDataController().getCurrentLevelID();
		int curSceneID = gController.getDataController().getCurrentSceneID();
		if(!levelExist.contains(newLevel)){
			gController.createLevel(newLevel);
			levelExist.add(newLevel);
		}
		gController.modifyGameSwitchSceneToNewLevelID(oldLevelID, newLevel, curSceneID);
		gController.switchScene(newLevel, curSceneID);
	}

}
