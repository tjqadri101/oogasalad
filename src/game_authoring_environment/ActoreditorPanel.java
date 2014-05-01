package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import controller.GAEController;

public class ActoreditorPanel extends Panel {

	private static final Integer[] levelList = {1,2,3,4,5,6,7,8,9,10};


	private SubPanel mySubPanel;
	private GAEController gController;
	private JTable myTable;

	public ActoreditorPanel(GAEController gController) {
		super(PanelType.ACTOREDITOR);

		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());		
		this.add(new JScrollPane(mySubPanel), BorderLayout.NORTH);
		this.add(new JScrollPane(createTable()), BorderLayout.CENTER);
		this.add(createButtonPanel(), BorderLayout.SOUTH);
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
	
	private JPanel createButtonPanel(){
		JPanel k = new JPanel();
		k.setLayout(new BorderLayout());
		k.add(createAnimationButton(), BorderLayout.NORTH);
		k.add(createDeleteButton(), BorderLayout.SOUTH);
		return k;
	}

	private JButton createAnimationButton(){
		JButton j = new JButton("Animate Actor");
		j.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				Object[] options1 = {"Animate Jump Image",
						"Animate Forward Move",
						"Animate Backward Move"};

				int result = JOptionPane.showOptionDialog(null,
						"Animation options",
						"Select an animation type",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						options1,
						null);			

					try{
						JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
						UIManager.put("FileChooser.openDialogTitleText", "Chooser");
						SwingUtilities.updateComponentTreeUI(chooser);
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"jpg", "gif","png","jpeg");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(getParent());
						if(returnVal == JFileChooser.APPROVE_OPTION) {


								String path = chooser.getSelectedFile().getPath();
								String name = chooser.getSelectedFile().getName();
								switch(result){
								case 0:
									gController.uploadImage(100,100, path);
									gController.modifyActorAnimationJump(name, 100,100);
									break;
								case 1:
									gController.uploadImage(100,100, path);
									gController.modifyActorAnimationFDMove(name, 100,100);
									break;
								case 2:
									gController.uploadImage(100,100, path);
									gController.modifyActorAnimationBKMove(name, 100,100);
									break;
								}
							}

						}
						catch(Exception l){

						}
			}
		});

		return j;
	}

	public void update(int currentSelectedActorID){
		updateTable();
	}

	private JTable createTable(){
		myTable = new ActorEditorTable(gController);

		return myTable;
	}

	public JButton createDeleteButton(){
		JButton b = new JButton("Delete Actor");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				gController.deleteActorNoID();
			}			
		});
		return b;
	}

	public void updateTable(){
		List<String> s = gController.getAttributes();
		String firstrow = s.get(0);
		String[] strings = firstrow.split(",");
		for(String k : strings){

		}
	}

	private JButton makeChooseButton(){
		JButton b = ViewFactory.createJButton("Select Actor Image");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				chooseActor("Select Actor ");
			}			
		});
		return b;
	}




	private void chooseActor(String displayText) {
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

				//gController.uploadImage(100, 100, path);
				gController.updateActorImage(path,name);
				gController.modifyActorImageNoID(name, 100,100);
			}			
		}catch(Exception e){
		}
	}


}
