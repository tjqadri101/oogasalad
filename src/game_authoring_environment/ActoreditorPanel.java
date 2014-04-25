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
	
	public void update(int currentSelectedActorID){
		updateTable();
		System.out.println("ActoreditorPanel switched to actorID:"+currentSelectedActorID);
	}

	private JTable createTable(){
		myTable = new ActorEditorTable(gController);
		
		return myTable;
	}
	
	
	public void updateTable(){
		List<String> s = gController.getAttributes();
		String firstrow = s.get(0);
		String[] strings = firstrow.split(",");
		for(String k : strings){
			//System.out.println(k);
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

				//gController.modifyActorImageNoID(path, 100, 100);

				gController.uploadImage(100, 100, path);
				gController.updateActorImage(path,name);
				gController.setActorImageURL(name);
			}			
		}catch(Exception e){
		}
	}
	

}
