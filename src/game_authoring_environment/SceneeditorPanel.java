package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GAEController;

public class SceneeditorPanel extends Panel {
	
	private GAEController gController;
	private SubPanel mySubPanel;
	private JTable myTable;

	
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
				gController.uploadImage(800, 600, path);
				gController.modifySceneBackground(path);
			}			
		}catch(Exception e){

		}

	}
	
	public JTable createTable(){
		myTable = new SceneEditorTable(gController);
		return myTable;
	}

}
