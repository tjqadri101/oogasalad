package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import objects.NonPlayer;
import controller.GAEController;

public class BehaviorsPanel extends Panel {

	private static final String BRICK_DEFAULT_IMAGE = "brick.png";
	private SubPanel mySubPanel;
	private GAEController gController;
	private JTable myTable; 
	private List<Object[]> listData; 
	private JTable tableWindow;
	private static final String[] collisionTypesStrings = {/*"Explode",*/ "HitterEliminateVictim", "PerishTogether", "StayOnObject", "Rebounce" /*,"StayOnTile", "KilledByTile"*/};
	private static final String[] collisionLocation = {"Up", "Down", "Left", "Right", "All"};


	public BehaviorsPanel(GAEController gController) {
		super(PanelType.BEHAVIORS);
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

		JButton enterTileMode = ViewFactory.createJButton("Enter Tile Mode");
		enterTileMode.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){

				enterTileEditingMode();
			}
		});

		JButton exitTileMode = ViewFactory.createJButton("Exit Tile Mode");
		exitTileMode.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				exitTileEditingMode();
			}
		});

		outPanel.add(enterTileMode,BorderLayout.NORTH);
		outPanel.add(exitTileMode,BorderLayout.SOUTH);

		return outPanel;
	}


	private void enterTileEditingMode(){
		JPanel panel = new JPanel(new GridLayout(0, 1));
		JTextField tf = new JTextField();
		panel.add(tf);
		String imageName = BRICK_DEFAULT_IMAGE;
		int result = JOptionPane.showConfirmDialog(null, panel, "Enter tile collision ID",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			JPanel panel2 = new JPanel(new GridLayout(0, 1));
			JLabel lb = new JLabel("Do you want to set the brick image? (Not mandatory)");
			panel2.add(lb);
			int result2 = JOptionPane.showConfirmDialog(null, panel2, "Confirm",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result2 == JOptionPane.OK_OPTION) {
				try{
					JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
					UIManager.put("FileChooser.openDialogTitleText", "Choose Tile Image");
					SwingUtilities.updateComponentTreeUI(chooser);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"jpg", "gif","png","jpeg");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(getParent());
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						imageName = chooser.getSelectedFile().getName();
						String path = chooser.getSelectedFile().getPath();
						gController.uploadImage(20, 20, path);
					}			
				}catch(Exception e){
				}
			}
			char colID = tf.getText().charAt(0);
			gController.setDragTile(colID, imageName);
			gController.getEngine().setTileEditing(true);
		}
	}

	private void exitTileEditingMode(){
		gController.getEngine().setTileEditing(false);
		System.out.println("TileEditingMode Exited");
	}


	public JTable createTableWindow(){
		System.out.println("hi");
		System.out.println("list data is" + listData);
		String[] columnNames = {"Hitter","Hittee","CollisionType"};
		System.out.println("hi");
		Object[][] data = new Object[listData.size()][3];
		System.out.println("hi");
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

		Map<Integer, NonPlayer> mapofNonPlayers = gController.getMapOfNonPlayers();
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