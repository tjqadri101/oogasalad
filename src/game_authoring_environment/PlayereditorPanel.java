package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import saladConstants.SaladConstants;
import controller.GAEController;

public class PlayereditorPanel extends Panel {
	
	private static final Integer[] levelList = {1,2,3,4,5,6,7,8,9,10};
	

	private SubPanel mySubPanel;
	private GAEController gController;
	private JTable myTable;
	private boolean playerExists;
	private String playerName;
	
	public PlayereditorPanel(GAEController gController) {
		super(PanelType.PLAYEREDITOR);
		this.gController = gController;
		makeSubPanel();
		playerExists = false;
		construct();
	}

	@Override
	protected void construct() {
		this.removeAll();
		this.setLayout(new BorderLayout());		
		this.add(new JScrollPane(mySubPanel), BorderLayout.NORTH);
		this.add(new JScrollPane(createTable()), BorderLayout.CENTER);
		if(!playerExists){
			System.out.println("test if");
			this.add(createPlayerButton(), BorderLayout.SOUTH);
		}
		else{
			System.out.println("test else");
			this.add(createKeySetButton(), BorderLayout.SOUTH);
	
		}	
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
		JPanel panel = new JPanel();
		JButton jb  = makeChooseButton();
		panel.add(jb);
		return jb;
	}
	public void update(){
		updateTable();
	}

	private JTable createTable(){
		System.out.println("test create tables");
		myTable = new PlayerEditorTable(gController, this);
		System.out.println("test create tables");
		return myTable;
	}
	
	public JButton createPlayerButton(){
		JButton button = new JButton("Create Player");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				
				gController.createPlayer(gController.getPlayerID(), null, 100, 100, 100, 100, myTable.getName(), 0, 1);
				playerExists = true;
				((PlayerEditorTable) myTable).setPlayerExists(true);
				makeSubPanel();
				construct();
				
				
			}
		});
		return button;
	}
	
	public JButton createKeySetButton(){
		JButton button = new JButton("Set Control Keys");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				String moveUp_ = null;
				String moveDown_ = null;
				String moveLeft_= null;
				String jump_ = null;
				String moveRight_ = null;
				String shoot_ = null;

				Map<String, Integer> map = gController.getKeyMap(SaladConstants.PLAYER_ID);
				for(String s : map.keySet()){
					
					char k = (char) (map.get(s).intValue());
					System.out.println(s + k);
					switch(s){
					case "moveUp": 
						moveUp_ = String.valueOf(k);
						break;
					case "moveDown": 
						moveDown_ = String.valueOf(k);
						break;
					case "moveLeft": 
						moveLeft_ = String.valueOf(k);
						break;
					case "moveRight": 
						moveRight_ = String.valueOf(k);
						break;
					case "shoot": 
						shoot_ = String.valueOf(k);
						break;
					case "jump": 
						jump_ = String.valueOf(k);
						break;
					}
				}
				
				JTextField moveUp = new JTextField(2);
				moveUp.setText(moveUp_);
				
				JTextField moveDown = new JTextField(2);
				moveDown.setText(moveDown_);
				
				JTextField moveLeft = new JTextField(2);
				moveLeft.setText(moveLeft_);
				
				JTextField moveRight = new JTextField(2);
				moveRight.setText(moveRight_);
				
				JTextField jump = new JTextField(2);
				jump.setText(jump_);
				
				JTextField shoot = new JTextField(2);
				shoot.setText(shoot_);
				

				JTextField[] texts = {moveUp, moveDown, moveLeft, moveRight, jump, shoot};
				String[] strings = {"MoveUp Key:", "MoveDown Key:", "MoveLeft Key:", "MoveRight Key:", "Jump Key:","Shoot Key:",};
				JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);
				int result = JOptionPane.showConfirmDialog(null, myPanel, 
						"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if(moveUp.getText().length()>0){
						int moveUp_char = moveUp.getText().toLowerCase().charAt(0);
						gController.modifyPlayerKeyUpNoID(moveUp_char);
					}
					if(moveDown.getText().length()>0){
						int moveDown_char =(int) moveDown.getText().toLowerCase().charAt(0);
						gController.modifyPlayerKeyDownNoID(moveDown_char);
					}
					if(moveLeft.getText().length()>0){
						int moveLeft_char =(int) moveLeft.getText().toLowerCase().charAt(0);
						gController.modifyPlayerKeyLeftNoID(moveLeft_char);
					}
					if(moveRight.getText().length()>0){
						int moveRight_char =(int) moveRight.getText().toLowerCase().charAt(0);
						gController.modifyPlayerKeyRighttNoID(moveRight_char);
					}
					if(jump.getText().length()>0){
						int jump_char = (int) jump.getText().toLowerCase().charAt(0);					
						gController.modifyPlayerKeyJumpNoID(jump_char);
					}
					if(shoot.getText().length()>0){
						int shoot_char = (int) shoot.getText().toLowerCase().charAt(0);
						gController.modifyPlayerKeyShoottNoID(shoot_char);
					}

				}

			}			
		});
		return button;
	}
	
	
	public void updateTable(){
		List<String> s = gController.getAttributes();
		String firstrow = s.get(0);
		String[] strings = firstrow.split(",");
		for(String k : strings){
			System.out.println(k);
		}
	}

	private JButton makeChooseButton(){
		JButton b = ViewFactory.createJButton("Select Player Image");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				chooseActor("Select Player Image");
			}			
		});
		return b;
	}

	
	
	
	public void chooseActor(String displayText) {
		try{
			JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
			UIManager.put("FileChooser.openDialogTitleText", displayText);
			SwingUtilities.updateComponentTreeUI(chooser);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"jpg", "gif","png","jpeg");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getPath();
				String name = chooser.getSelectedFile().getName();

				gController.modifyPlayerImageNoID(path, 100, 100);
				
			}			
		}catch(Exception e){
		}
	}
	
	public void updateInfo(int actorID){
		System.out.println("updating playerID:"+actorID);	
	}

	public boolean doesPlayerExist() {
		return playerExists;
	}

}

