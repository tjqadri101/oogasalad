package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GAEController;

public class ActoreditorPanel extends Panel {
	
	private SubPanel mySubPanel;
	private PanelType superType;
	private JComponent myComponent;
	private GAEController gController;
	
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
		this.add(makeTable(), BorderLayout.CENTER);
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
		JButton jb  = makeChooseImageButton();
		return jb;
	}
	public void update(){
		
	}
	
	public JComponent makeTable(){
		 
		 String[] columnNames = {"Category",
				  "Value",
				  "Type"};
				
				Object[][] data = {
				{"Name", new String(),
				"text"},
				{"Time", new Integer(0),
				"real"},
				{"Position X", new Integer(0),
				"real"},
				{"Position Y", new Integer(0),
				"real"},
				
				//{"Actor Tags", new String(), "text"}
				};
		JComponent k = ViewFactory.createScrollingTable(columnNames, data);
				return k;
	}
	
	public void updateTable(){
		
	}
	
	private JButton makeChooseImageButton(){
		JButton b = ViewFactory.createJButton("Select Actor Image");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				chooseActorImage("Select Actor Image");
			}			
		});
		return b;
	}
	
	private void chooseActorImage(String displayText) {
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
				gController.updateActorImage(path,name);
			}			
		}catch(Exception e){
			
		}
		
	}

}
