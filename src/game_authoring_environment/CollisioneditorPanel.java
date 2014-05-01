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
import javax.swing.JCheckBox;
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
import saladConstants.SaladConstants;
import controller.GAEController;

public class CollisioneditorPanel extends Panel {


	private static final String BRICK_DEFAULT_IMAGE = "brick.png";
	private SubPanel mySubPanel;
	private GAEController gController;
	private JTable myTable; 
	private List<Object[]> listData; 
	private JTable tableWindow;
	private static final String[] collision0TypesStrings = {"HitterEliminateVictim", "PerishTogether", "StayOnObject", "Rebound", "ShootHitObject", "Restore Blood", "Add Life", "Add Score"};
	private static final String[] collision1TypesStrings = {"StayOnTile", "KilledByTile", "Restore Blood", "Add Life", "Add Score"};
	private static final String[] collision2TypesStrings = {"StayOnTile", "KilledByTile"};
	private static final String[] collision3TypesStrings = {"Rebound", "ShootHitObject"};

	private static final String[] collisionLocation = {SaladConstants.Top, SaladConstants.BOTTOM, SaladConstants.LEFT, SaladConstants.RIGHT, SaladConstants.ALL};


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
			i++;
		}

		myTable = new JTable(data, columnNames);
		return myTable;

	}

	public String[] createPlayerList(){
		String[] players = {"Player"};
		return players;
	}
	
	public String[] createTileColIDList(){
		List<Character> chars = gController.getTileColIDs();
		String[] tilIDs = new String[chars.size()];
		for(int i=0; i<chars.size(); i++){
			tilIDs[i] = chars.get(i).toString();
		}
		return tilIDs;
	}
	
	
	public Integer[] createActorCollisionList(){

		Map<Integer, NonPlayer> mapofNonPlayers = gController.getMapOfNonPlayers();
		TreeSet<Integer> colIDs = new TreeSet<Integer>();
		for(Integer i : mapofNonPlayers.keySet()){
			NonPlayer p = mapofNonPlayers.get(i);
			colIDs.add(p.colid);
		}
		Integer[] colids = new Integer[colIDs.size()]; 
		int j=0;
		for(Integer i : colIDs){
			colids[j] = i;
			j++;
		}
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
				Object[] options1 = {"Player to Actor",
						"Player to Tile",
						"Actor to Tile", "Actor to Player", "Actor to Actor","Cancel"};

				int result = JOptionPane.showOptionDialog(null,
						null,null,
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						options1,
						null);		
				//player to actor
				if(result == 0){
					JComboBox hitterBox = new JComboBox(createPlayerList());
					JComboBox hitteeBox = new JComboBox(createActorCollisionList());
					JComboBox collisionTypes = new JComboBox(collision0TypesStrings);
					JComboBox[] texts = {hitterBox, hitteeBox, collisionTypes};
					String[] strings = {"ID of Hitter:", "ID being Hit:", "Type of Collision:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

					result = JOptionPane.showConfirmDialog(null, myPanel, 
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
							int hitter = 1;
							int hittee = Integer.parseInt(hitteeBox.getSelectedItem().toString());
							String location = collisionLocationBox.getSelectedItem().toString();

							switch(str){
								case "HitterEliminateVictim":{
									gController.modifyCollisBehavHitElimVic(hittee, hitter,location);
									break;
								}
	
								case "ShootHitObject":{
									gController.modifyCollisBehavShootHitObject(hittee, hitter, location);
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
								case "Restore Blood":{
									JTextField score = new JTextField(10);
									JTextField blood = new JTextField(10);
									JComponent[] texts2 = {score, blood};
									String[] strings2 = {"Score:", "Amount of Blood:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyBloodManagerCollision(Integer.parseInt(score.getText().toString()), Integer.parseInt(blood.getText().toString()), hittee, hitter);
									}
									break;
								}
									
								case "Add Life":{
									JTextField score = new JTextField(10);
									JComponent[] texts2 = {score};
									String[] strings2 = {"Number of Lives:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyLifeManagerSetCollisionLife(Integer.parseInt(score.getText().toString()), hittee, hitter);
									}
									break;
								}
								case "Add Score":{
									JTextField score = new JTextField(10);
									JComponent[] texts2 = {score};
									String[] strings2 = {"Number of Lives:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyScoreManagerCollisionScore(Integer.parseInt(score.getText().toString()), hittee, hitter);
									}
									break;
								}
							}
							makeSubPanel();
							construct();
						}
					}
				}
				else if(result == 1){
					JComboBox hitterBox = new JComboBox(createPlayerList());
					JComboBox hitteeBox = new JComboBox(createTileColIDList());
					JComboBox collisionTypes = new JComboBox(collision1TypesStrings);
					JComboBox[] texts = {hitterBox, hitteeBox, collisionTypes};
					String[] strings = {"ID of Hitter:", "ID being Hit:", "Type of Collision:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

					result = JOptionPane.showConfirmDialog(null, myPanel, 
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
							int hitter = 0;
							char hittee = hitteeBox.getSelectedItem().toString().charAt(0);
							String location = collisionLocationBox.getSelectedItem().toString();

							switch(str){
								case "StayOnTile":{
									gController.modifyCollisBehavStayOnTile(hitter, hittee, location);
									break;
								}
	
								case "KilledByTile":{
									gController.modifyCollisionBehaviorToDieByTile(hitter, hittee, location);
									break;
								}
								case "Restore Blood":{
									JTextField score = new JTextField(10);
									JTextField blood = new JTextField(10);
									JComponent[] texts2 = {score, blood};
									String[] strings2 = {"Score:", "Amount of Blood:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyBloodManagerTileCollision(Integer.parseInt(score.getText().toString()), hitter, hittee);
									}
									break;
								}
									
								case "Add Life":{
									JTextField score = new JTextField(10);
									JComponent[] texts2 = {score};
									String[] strings2 = {"Number of Lives:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyLifeManagerSetTileCollisionLife(Integer.parseInt(score.getText().toString()), hitter, hittee);
									}
									break;
								}
								case "Add Score":{
									JTextField score = new JTextField(10);
									JComponent[] texts2 = {score};
									String[] strings2 = {"Number of Lives:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyScoreManagerTileCollisionScore(Integer.parseInt(score.getText().toString()), hitter, hittee);
									}
									break;
								}
							}
							makeSubPanel();
							construct();
						}
					}
				}
				else if(result == 2){
					JComboBox hitterBox = new JComboBox(createActorCollisionList());
					JComboBox hitteeBox = new JComboBox(createTileColIDList());
					JComboBox collisionTypes = new JComboBox(collision2TypesStrings);
					JComboBox[] texts = {hitterBox, hitteeBox, collisionTypes};
					String[] strings = {"ID of Hitter:", "ID being Hit:", "Type of Collision:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

					result = JOptionPane.showConfirmDialog(null, myPanel, 
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
							char hittee = hitteeBox.getSelectedItem().toString().charAt(0);
							String location = collisionLocationBox.getSelectedItem().toString();

							switch(str){
								case "StayOnTile":{
									gController.modifyCollisBehavStayOnTile(hitter, hittee, location);
									break;
								}
	
								case "KilledByTile":{
								//	gController.modifyCollisionBehaviorToDieByTile(hitter, hittee, location);
									break;
								}
							}
							makeSubPanel();
							construct();
						}
					}
				}
				else if(result == 3){
					JComboBox hitterBox = new JComboBox(createActorCollisionList());
					JComboBox hitteeBox = new JComboBox(createPlayerList());
					JComboBox collisionTypes = new JComboBox(collision3TypesStrings);
					JComboBox[] texts = {hitterBox, hitteeBox, collisionTypes};
					String[] strings = {"ID of Hitter:", "ID being Hit:", "Type of Collision:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

					result = JOptionPane.showConfirmDialog(null, myPanel, 
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
							int hittee = 0;
							String location = collisionLocationBox.getSelectedItem().toString();

							switch(str){
								case "Rebound":{
									gController.modifyCollisionBehaviorRebounce(hitter, hittee, location);
									break;
								}
	
								case "ShootHitObject":{
									gController.modifyCollisBehavShootHitObject(hitter, hittee, location);
									break;
								}
							}
							makeSubPanel();
							construct();
						}
					}
				}
				//actor to actor (Added by Nick)
				if(result == 4){
					JTextField hitterBox = new JTextField(20);
					JTextField hitteeBox = new JTextField(20);
					JComboBox collisionTypes = new JComboBox(collision0TypesStrings);
					JComponent[] texts = {hitterBox, hitteeBox, collisionTypes};
					String[] strings = {"ID of Hitter:", "ID being Hit:", "Type of Collision:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

					result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						String str = collisionTypes.getSelectedItem().toString();
						Object[] k = {hitterBox.getText(), hitteeBox.getText(), str};
						listData.add(k);
						JComboBox collisionLocationBox = new JComboBox(collisionLocation);
						JComboBox[] texts_ = {collisionLocationBox};
						String[] strings_ = {"Where Can Collision Take Place"};
						JPanel myPanel_ = ViewFactory.createOptionInputPanel(texts_, strings_);
						int result_ = JOptionPane.showConfirmDialog(null, myPanel_, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result_ == JOptionPane.OK_OPTION) {
							int hitter = Integer.parseInt(hitterBox.getText());
							int hittee = Integer.parseInt(hitteeBox.getText());
							String location = collisionLocationBox.getSelectedItem().toString();

							switch(str){
								case "HitterEliminateVictim":{
									gController.modifyCollisBehavHitElimVic(hittee, hitter,location);
									break;
								}
	
								case "ShootHitObject":{
									gController.modifyCollisBehavShootHitObject(hittee, hitter, location);
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
								case "Restore Blood":{
									JTextField score = new JTextField(10);
									JTextField blood = new JTextField(10);
									JComponent[] texts2 = {score, blood};
									String[] strings2 = {"Score:", "Amount of Blood:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyBloodManagerCollision(Integer.parseInt(score.getText().toString()), Integer.parseInt(blood.getText().toString()), hittee, hitter);
									}
									break;
								}
									
								case "Add Life":{
									JTextField score = new JTextField(10);
									JComponent[] texts2 = {score};
									String[] strings2 = {"Number of Lives:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyLifeManagerSetCollisionLife(Integer.parseInt(score.getText().toString()), hittee, hitter);
									}
									break;
								}
								case "Add Score":{
									JTextField score = new JTextField(10);
									JComponent[] texts2 = {score};
									String[] strings2 = {"Number of Lives:"};
									JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

									int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
											"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
									if (result2 == JOptionPane.OK_OPTION) {
									gController.modifyScoreManagerCollisionScore(Integer.parseInt(score.getText().toString()), hittee, hitter);
									}
									break;
								}
							}
							makeSubPanel();
							construct();
						}
					}
				}
				
			}
		};
			return a;
	}
}



