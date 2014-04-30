package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import objects.NonPlayer;
import controller.GAEController;

public class CollisioneditorPanel extends Panel {
	

	private static final String BRICK_DEFAULT_IMAGE = "brick.png";
	private SubPanel mySubPanel;
	private GAEController gController;
	private JTable myTable; 
	private List<Object[]> listData; 
	private JTable tableWindow;
	private static final String[] collisionTypesStrings = {/*"Explode",*/ "HitterEliminateVictim", "PerishTogether", "StayOnObject", "Rebounce" /*,"StayOnTile", "KilledByTile"*/};
	private static final String[] collisionLocation = {"Up", "Down", "Left", "Right", "All"};


	public CollisioneditorPanel(GAEController gController) {
		super(PanelType.COLLISIONEDITOR);
		this.gController = gController;
		listData = new ArrayList<Object[]>();
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(mySubPanel),BorderLayout.NORTH);
		this.add(addCollisionButton(),BorderLayout.SOUTH);
		this.add(new JScrollPane(createTableWindow()), BorderLayout.CENTER);

	}

	@Override
	protected void makeSubPanel() {
		mySubPanel = (SubPanel) ViewFactory.buildPanel(PanelType.SUB,gController);
		mySubPanel.setSuperType(getType());
		mySubPanel.addItems(makeSubPanelItems());
		mySubPanel.construct();		
	}

	@Override
	protected JComponent makeSubPanelItems() {
		JPanel outPanel = new JPanel();
		outPanel.setLayout(new BorderLayout());	

		return outPanel;
	}
	
	
	public JTable createTableWindow(){
		String[] columnNames = {"Hitter","Hittee","CollisionType"};
		Object[][] data = new Object[listData.size()][3];
		int i=0;

		for(Object[] d:listData){
			data[i]= d;
			System.out.println(d);
			i++;
		}

		myTable = new JTable(data, columnNames);
		return myTable;

	}

	public Integer[] createCollisionList(){

		Map<Integer, NonPlayer> mapofNonPlayers = gController.getMapOfPlayers();
		TreeSet<Integer> colIDs = new TreeSet<Integer>();
		for(Integer i : mapofNonPlayers.keySet()){
			NonPlayer p = mapofNonPlayers.get(i);
			colIDs.add(p.colid);
		}
		Integer[] colids = new Integer[colIDs.size()+1]; 
		int j=0;
		for(Integer i : colIDs){
			colids[j] = i;
			j++;
		}
		colids[j]=2;
		return colids;
	}

	public JButton addCollisionButton(){
		JButton j = new JButton("Add a collision");
		j.addActionListener(CollisionButtonListener());

		return j;
	}

	private ActionListener CollisionButtonListener(){ 
		ActionListener a = new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				JComboBox hitterBox = new JComboBox(createCollisionList());
				JComboBox hitteeBox = new JComboBox(createCollisionList());
				JComboBox collisionTypes = new JComboBox(collisionTypesStrings);
				JComboBox[] texts = {hitterBox, hitteeBox, collisionTypes};
				String[] strings = {"ID of Hitter:", "ID being Hit:", "Type of Collision:"};
				JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

				int result = JOptionPane.showConfirmDialog(null, myPanel, 
						"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					String str = collisionTypes.getSelectedItem().toString();
					Object[] k = {hitterBox.getSelectedItem().toString(), hitteeBox.getSelectedItem().toString(), str};
					listData.add(k);
					JComboBox collisionLocationBox = new JComboBox(collisionLocation);
					JComboBox[] texts_ = {collisionLocationBox};
					String[] strings_ = {"Where Can Collision Take Place"};
					JPanel myPanel_ = ViewFactory.createOptionInputPanel(texts_, strings_);
					int result_ = JOptionPane.showConfirmDialog(null, myPanel_, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result_ == JOptionPane.OK_OPTION) {
						int hitter = Integer.parseInt(hitterBox.getSelectedItem().toString());
						int hittee = Integer.parseInt(hitteeBox.getSelectedItem().toString());
						String location = collisionLocationBox.getSelectedItem().toString();

						switch(str){
	
							case "HitterEliminateVictim":{
								System.out.println("");
								gController.modifyCollisBehavHitElimVic(hittee, hitter,location);
								break;
							}
	
							case "Explode":{
	
								break;
							}
							case "PerishTogether":{
								gController.modifyCollisBehavPerishTog(hittee, hitter,location);
	
								break;
							}
							case "Rebounce":{
								gController.modifyCollisionBehaviorRebounce(hittee, hitter,location);
	
								break;
							}
							case "StayOnTile":{
								break;
							}
							case "KilledByTile":{
	
								break;
							}
						}
						makeSubPanel();
						construct();
					}
				}
				}
			};
			return a;
		}
}


