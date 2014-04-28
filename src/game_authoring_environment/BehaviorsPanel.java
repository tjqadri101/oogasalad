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
	private static final String[] collisionTypesStrings = {"Explode", "HitterEliminateVictim", "PerishTogether", "StayOnObject", "Rebounce", "StayOnTile", "KilledByTile"};

	
	public BehaviorsPanel(GAEController gController) {
		super(PanelType.BEHAVIORS);
		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(mySubPanel),BorderLayout.NORTH);
		this.add(addCollisionButton(),BorderLayout.SOUTH);
		this.add(new JScrollPane(createTable()), BorderLayout.CENTER);

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
					JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
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

	public JTable createTable(){
		String[] columnNames = {"Hitter","Hittee","CollisionType"};

		Object[][] data = {
				{"Kathy", "Smith",
				"Snowboarding"},
				{"John", "Doe",
				"Rowing"},
				{"Sue", "Black",
				"Knitting"}};
		myTable = new JTable(data, columnNames);
		return myTable;
	}
	public Integer[] createCollisionPanel(){
		JPanel myPanel = new JPanel();
		Map<Integer, NonPlayer> mapofNonPlayers = gController.getMapOfPlayers();
		TreeSet<Integer> colIDs = new TreeSet<Integer>();
		for(Integer i : mapofNonPlayers.keySet()){
			NonPlayer p = mapofNonPlayers.get(i);
			colIDs.add(p.getColID());
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
		j.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				JComboBox hitterBox = new JComboBox(createCollisionPanel());
				JComboBox hitteeBox = new JComboBox(createCollisionPanel());
				JComboBox collisionTypes = new JComboBox(collisionTypesStrings);
				JComboBox[] texts = {hitterBox, hitteeBox, collisionTypes};
				String[] strings = {"ID of Hitter:", "ID being Hit:", "Type of Collision:"};
				JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

				int result = JOptionPane.showConfirmDialog(null, myPanel, 
						"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
				//	gController.modifyActorSlowShootNoID("bullet.png",  Integer.parseInt(xSizeField.getText()), Integer.parseInt(ySizeField.getText()), 100, Integer.parseInt(speedField.getText()));
				}
			}
		});

		return j;



	}
}