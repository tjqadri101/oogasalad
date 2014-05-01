package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

import objects.NonPlayer;
import saladConstants.SaladConstants;
import controller.GAEController;

public class SceneeditorPanel extends Panel {

	private GAEController gController;
	private SubPanel mySubPanel;
	private JTable myTable;

	private static final String[] collisionLocation = {SaladConstants.Top, SaladConstants.BOTTOM, SaladConstants.LEFT, SaladConstants.RIGHT, SaladConstants.ALL};


	public SceneeditorPanel(GAEController gController) {
		super(PanelType.SCENEEDITOR);
		this.gController = gController;
		makeSubPanel();
		construct();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());		
		this.add(mySubPanel, BorderLayout.NORTH);
		this.add(new JScrollPane(createTable()), BorderLayout.CENTER);
		this.add(makebuttonpanel(), BorderLayout.SOUTH);

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
		JButton jb  = makeChooseButton();
		return jb;
	}

	       private JButton makeEnemyShower(){
	                JButton b = ViewFactory.createJButton("Create Enemy Shower");
	                b.addActionListener(new ActionListener(){
	                        @Override
	                        public void actionPerformed (ActionEvent e){
	                                JTextField maxEnemies = new JTextField(10);
	                                JTextField ImageName = new JTextField(10);
	                                JComponent[] texts2 = {maxEnemies, ImageName};
	                                String[] strings2 = {"maxEnemies", "ImageName"};
	                                JPanel myPanel2 = ViewFactory.createOptionInputPanel(texts2, strings2);

	                                int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
	                                                "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
	                                if (result2 == JOptionPane.OK_OPTION) {
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

	                                                int result3 = JOptionPane.showConfirmDialog(null, myPanel, 
	                                                                "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
	                                                if (result3 == JOptionPane.OK_OPTION) {
	                                                        Object[] k = {hitterBox.getSelectedItem().toString(), hitteeBox.getSelectedItem().toString()};

	                                                        JComboBox collisionLocationBox = new JComboBox(collisionLocation);
	                                                        JComboBox[] texts_ = {collisionLocationBox};
	                                                        String[] strings_ = {"Where Can Collision Take Place"};
	                                                        JPanel myPanel_ = ViewFactory.createOptionInputPanel(texts_, strings_);
	                                                        int result_ = JOptionPane.showConfirmDialog(null, myPanel_, 
	                                                                        "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
	                                                        if (result_ == JOptionPane.OK_OPTION) {
	                                                                int k2 = gController.getEventTriggerPair();
	                                                                int hitter = 0;
	                                                                int hittee = Integer.parseInt(hitteeBox.getSelectedItem().toString());
	                                                                String location = collisionLocationBox.getSelectedItem().toString();

	                                                                gController.modifyTriggerEventManagerEnemyShower(k2, Integer.parseInt(maxEnemies.getText()), ImageName.getText().toString());

	                                                        }
	                                                }
	                                        }
	                                        else if(result == 1){
	                                                JComboBox hitterBox = new JComboBox(createPlayerList());
	                                                JComboBox hitteeBox = new JComboBox(createTileColIDList());
	                                                JComboBox[] texts = {hitterBox, hitteeBox};
	                                                String[] strings = {"ID of Hitter:", "ID being Hit:"};
	                                                JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

	                                                int result3 = JOptionPane.showConfirmDialog(null, myPanel, 
	                                                                "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
	                                                if (result3 == JOptionPane.OK_OPTION) {
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
	                                                                JComponent[] texts4 = {xpos, ypos, xsize, ysize};
	                                                                String[] strings4 = {"X Position :", "Y Position:", "X Size:", "Y Size:"};
	                                                                JPanel myPanel4 = ViewFactory.createOptionInputPanel(texts4, strings4);

	                                                                int result4 = JOptionPane.showConfirmDialog(null, myPanel4, 
	                                                                                "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
	                                                                if (result4 == JOptionPane.OK_OPTION) {
	                                                                        int k2 = gController.getEventTriggerPair();
	                                                                        int hitter = 0;
	                                                                        int hittee = Integer.parseInt(hitteeBox.getSelectedItem().toString());
	                                                                        String location = collisionLocationBox.getSelectedItem().toString();
	                                                                        gController.modifyTriggerEventManagerEnemyShower(k2, Integer.parseInt(maxEnemies.getText()), ImageName.getText().toString());
	                                                                        gController.modifyTriggerEventManagerTileCollision(k2, 0, Integer.parseInt(xpos.getText()), Integer.parseInt(ypos.getText()), Integer.parseInt(xsize.getText()), Integer.parseInt(ysize.getText())) ;
	                                                                }
	                                                        }
	                                                }
	                                        }
	                                        else if(result==2){

	                                                        JTextField time= new JTextField(10);
	                                                        JComponent[] texts4 = {time};
	                                                        String[] strings4 = {"At What Time"};
	                                                        JPanel myPanel4 = ViewFactory.createOptionInputPanel(texts4, strings4);

	                                                        int result4 = JOptionPane.showConfirmDialog(null, myPanel4, 
	                                                                        "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
	                                                        if (result4 == JOptionPane.OK_OPTION) {
	                                                                int k2 = gController.getEventTriggerPair();
	                                                                gController.modifyTriggerEventManagerEnemyShower(k2, Integer.parseInt(maxEnemies.getText()), ImageName.getText().toString());
	                                                                gController.modifyTriggerEventManagerTime(k2, Integer.parseInt(time.getText()));
	                                                        }


	                                }
	                                }
	                                }

	                        });
	                return b;
	                }

	       
	/* This part commented out steve wrote this
	 * 

	private JButton makeLevelDone(){
		JButton b = ViewFactory.createJButton("Create Level Done");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				JTextField maxEnemies = new JTextField(10);
				JTextField ImageName = new JTextField(10);
				JComponent[] texts2 = {maxEnemies, ImageName};
				String[] strings2 = {"maxEnemies", "ImageName"};
				JButton myPanel2 = ViewFactory.createJButton("Event Level Done");

				int result2 = JOptionPane.showConfirmDialog(null, myPanel2, 
						"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
				if (result2 == JOptionPane.OK_OPTION) {
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

						int result3 = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result3 == JOptionPane.OK_OPTION) {
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
								gController.modifyTriggerEventManagerLevelDone(k2); 
							}
						}
					}
					else if(result == 1){
						JComboBox hitterBox = new JComboBox(createPlayerList());
						JComboBox hitteeBox = new JComboBox(createTileColIDList());
						JComboBox[] texts = {hitterBox, hitteeBox};
						String[] strings = {"ID of Hitter:", "ID being Hit:"};
						JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

						int result3 = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
						if (result3 == JOptionPane.OK_OPTION) {
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
								JComponent[] texts4 = {xpos, ypos, xsize, ysize};
								String[] strings4 = {"X Position :", "Y Position:", "X Size:", "Y Size:"};
								JPanel myPanel4 = ViewFactory.createOptionInputPanel(texts4, strings4);

								int result4 = JOptionPane.showConfirmDialog(null, myPanel4, 
										"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
								if (result4 == JOptionPane.OK_OPTION) {
									int k2 = gController.getEventTriggerPair();
									int hitter = 1;
									int hittee = Integer.parseInt(hitteeBox.getSelectedItem().toString());
									String location = collisionLocationBox.getSelectedItem().toString();
									gController.modifyTriggerEventManagerLevelDone(k2);
									gController.modifyTriggerEventManagerTileCollision(k2, 0, Integer.parseInt(xpos.getText()), Integer.parseInt(ypos.getText()), Integer.parseInt(xsize.getText()), Integer.parseInt(ysize.getText())) ;
								}
							}
						}
					}
					else if(result==2){

							JTextField time= new JTextField(10);
							JComponent[] texts4 = {time};
							String[] strings4 = {"At What Time"};
							JPanel myPanel4 = ViewFactory.createOptionInputPanel(texts4, strings4);

							int result4 = JOptionPane.showConfirmDialog(null, myPanel4, 
									"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
							if (result4 == JOptionPane.OK_OPTION) {
								int k2 = gController.getEventTriggerPair();
								gController.modifyTriggerEventManagerLevelDone(k2);
								gController.modifyTriggerEventManagerTime(k2, Integer.parseInt(time.getText()));
							}
				}
				}
				}

			});
		return b;
		}*/

		private JPanel makebuttonpanel(){
			JPanel k = new JPanel();
			k.setLayout(new BorderLayout());
			k.add(makeEnemyShower(), BorderLayout.NORTH);
			// k.add(makeLevelDone(), BorderLayout.XX);
			k.add(endLevelButton(), BorderLayout.SOUTH);
			return k;
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
		public String[] createPlayerList(){
			String[] players = {"Player"};
			return players;
		}

		private JButton endLevelButton(){
			JButton b = ViewFactory.createJButton("End Level Event");
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
								gController.modifyTriggerEventManagerLevelDone(k2);
								gController.modifyTriggerEventManagerCollision(k2, hittee, hitter);
// OTHER METHOD NEEDED...(Nick added)

							}
						}
					}
					if(result == 1){
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
									int hitter = 1;
									int hittee = Integer.parseInt(hitteeBox.getSelectedItem().toString());
									String location = collisionLocationBox.getSelectedItem().toString();
									gController.modifyTriggerEventManagerLevelDone(k2);
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
									gController.modifyTriggerEventManagerLevelDone(k2);
									gController.modifyTriggerEventManagerTime(k2, Integer.parseInt(time.getText())) ;

						}
					}
				}


			});
			return b;
		}
		
		


		private JButton makeChooseButton(){
			JButton b = ViewFactory.createJButton("Select Background ");
			b.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed (ActionEvent e){
					chooseActor("Select Background");
				}			
			});
			return b;
		}




		private void chooseActor(String displayText) {
			JTextField xSize = new JTextField(10);
			JTextField ySize = new JTextField(10);
			JCheckBox verticalWrap = new JCheckBox();
			JCheckBox horizontalWrap = new JCheckBox();
			JComponent[] texts = {xSize, ySize, verticalWrap, horizontalWrap};
			String[] strings = {"Width:", "Height:", "Wrap Vertically?", "Wrap Horizontally?"};
			JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);

			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try{
					JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
					UIManager.put("FileChooser.openDialogTitleText", displayText);
					SwingUtilities.updateComponentTreeUI(chooser);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"jpg", "gif","png","jpeg");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(getParent());
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						String path = chooser.getSelectedFile().getPath();
						String name = chooser.getSelectedFile().getName();

						gController.uploadImage(Integer.parseInt(xSize.getText().toString())*20, Integer.parseInt(ySize.getText().toString())*20, path);
						gController.modifySceneBackground(name, horizontalWrap.isSelected(), verticalWrap.isSelected(), Integer.parseInt(xSize.getText().toString()), Integer.parseInt(ySize.getText().toString()));
					}

				}
				catch(Exception e){

				}
			}

		}

		public JTable createTable(){
			myTable = new SceneEditorTable(gController);
			return myTable;
		}

	}
