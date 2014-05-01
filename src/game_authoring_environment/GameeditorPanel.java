package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import objects.NonPlayer;
import saladConstants.SaladConstants;
import controller.GAEController;

public class GameeditorPanel extends Panel {
	
	private SubPanel mySubPanel;
	private PanelType superType;
	private GAEController gController;
	private String GameName;
	private Integer DisplayWidth;
	private Integer DisplayHeight;
	private DefaultTableModel myTableModel;
	private JTable myTable;
	private static final String[] collisionLocation = {SaladConstants.Top, SaladConstants.BOTTOM, SaladConstants.LEFT, SaladConstants.RIGHT, SaladConstants.ALL};

	
	public GameeditorPanel(GAEController gController) {
		super(PanelType.GAMEEDITOR);
		this.gController = gController;
		setDefaultValues();
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());		
		this.add(new JScrollPane(mySubPanel), BorderLayout.NORTH);
		this.add(endGameButton(), BorderLayout.SOUTH);
		this.add(new JScrollPane(createTable()), BorderLayout.CENTER);
	

	}
	
	private void setDefaultValues(){
		GameName = "";
		DisplayWidth = 480;
		DisplayHeight = 360;
		
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
	
	
	
	@Override
	protected void makeSubPanel() {
		mySubPanel = (SubPanel) ViewFactory.buildPanel(PanelType.SUB,gController);
		mySubPanel.setSuperType(getType());
		mySubPanel.addItems(makeSubPanelItems());
		mySubPanel.construct();
	}

	@Override
	protected JComponent makeSubPanelItems() {
		JButton jb = ViewFactory.createJButton("Validate");
		return jb;
	}
	
	
	public JTable createTable(){
		GameEditorTable table = new GameEditorTable(gController);
		
		return table;
	}
	
	private JButton endGameButton(){
		JButton b = ViewFactory.createJButton("End Scene Event");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				Object[] options1 = {"Collision",
						"Tile Collision", "Time", "Cancel"};

				int result = JOptionPane.showOptionDialog(null,"Trigger Event By...",null,
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						options1,
						null);		
				if(result == 0){
					JComboBox hitterBox = new JComboBox(createPlayerList());
					JComboBox hitteeBox = new JComboBox(createActorCollisionList());
					JComboBox[] texts = {hitterBox, hitteeBox};
					String[] strings = {"ID of Hitter:", "ID being Hit:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

					result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Object[] k = {hitterBox.getSelectedItem().toString(), hitteeBox.getSelectedItem().toString()};

						JComboBox collisionLocationBox = new JComboBox(collisionLocation);
						JComboBox[] texts_ = {collisionLocationBox};
						String[] strings_ = {"Where Can Collision Take Place"};
						JPanel myPanel_ = ViewFactory.createOptionInputPanel(texts_, strings_);
						int result_ = JOptionPane.showConfirmDialog(null, myPanel_, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result_ == JOptionPane.OK_OPTION) {
							int k2 = gController.getEventTriggerPair();
							int hitter = 1;
							int hittee = Integer.parseInt(hitteeBox.getSelectedItem().toString());
							String location = collisionLocationBox.getSelectedItem().toString();

							gController.modifyTriggerEventManagerLoseGame(k2);
//OTHER METHOD NEEDED...

						}
					}
				}
				else if(result == 1){
					JComboBox hitterBox = new JComboBox(createPlayerList());
					JComboBox hitteeBox = new JComboBox(createTileColIDList());
					JComboBox[] texts = {hitterBox, hitteeBox};
					String[] strings = {"ID of Hitter:", "ID being Hit:"};
					JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

					result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Object[] k = {hitterBox.getSelectedItem().toString(), hitteeBox.getSelectedItem().toString()};

						JComboBox collisionLocationBox = new JComboBox(collisionLocation);
						JComboBox[] texts_ = {collisionLocationBox};
						String[] strings_ = {"Where Can Collision Take Place"};
						JPanel myPanel_ = ViewFactory.createOptionInputPanel(texts_, strings_);
						int result_ = JOptionPane.showConfirmDialog(null, myPanel_, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result_ == JOptionPane.OK_OPTION) {
							JTextField xpos = new JTextField(10);
							JTextField ypos = new JTextField(10);
							JTextField ysize = new JTextField(10);
							JTextField xsize = new JTextField(10);
							JComponent[] texts2 = {xpos, ypos, xsize, ysize};
							String[] strings2 = {"X Position :", "Y Position:", "X Size:", "Y Size:"};
							JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

							int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result2 == JOptionPane.OK_OPTION) {
								int k2 = gController.getEventTriggerPair();
								int hitter = 0;
								int hittee = Integer.parseInt(hitteeBox.getSelectedItem().toString());
								String location = collisionLocationBox.getSelectedItem().toString();
								gController.modifyTriggerEventManagerLoseGame(k2);
								gController.modifyTriggerEventManagerTileCollision(k2, 0, Integer.parseInt(xpos.getText()), Integer.parseInt(ypos.getText()), Integer.parseInt(xsize.getText()), Integer.parseInt(ysize.getText())) ;

							}
						}
					}
				}
				else if(result==2){
							JTextField time = new JTextField(10);
							JComponent[] texts2 = {time};
							String[] strings2 = {"Time:"};
							JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);
							int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result2 == JOptionPane.OK_OPTION) {
								int k2 = gController.getEventTriggerPair();
								gController.modifyTriggerEventManagerLoseGame(k2);
								gController.modifyTriggerEventManagerTime(k2, Integer.parseInt(time.getText())) ;

					}
				}
			}


		});
		return b;
	}


	
	public void update(){
		construct();
	}
	
	
}

