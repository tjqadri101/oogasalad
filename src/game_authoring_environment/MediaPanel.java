package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GAEController;

public class MediaPanel extends Panel {
	
	private SubPanel mySubPanel;
	private JList myMediaList;
	private int mySeletedIndex = -1;
	private DefaultListModel listModel = new DefaultListModel();
	private GAEController gController;
	
	public MediaPanel(GAEController gController){
		super(PanelType.MEDIA);
		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		makeActorsList();
		this.setLayout(new BorderLayout());
		this.add(mySubPanel,BorderLayout.NORTH);
		this.add(myMediaList,BorderLayout.CENTER);
	}

	private void makeActorsList() {
		myMediaList = new JList();
		myMediaList.setModel(listModel);
		MouseListener mouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		    	 if (e.getClickCount() == 1 && listModel.size()!= 0) {
		    		 //single clicked
		    		 mySeletedIndex = myMediaList.locationToIndex(e.getPoint());	
		    		 //need to change attribute display to current actor
		          }
		    	 
		    	 else if (e.getClickCount() == 2 && listModel.size()!= 0) {
		             //double clicked
		          }
		     }
		 };
		 myMediaList.addMouseListener(mouseListener);
		
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
		
		JButton addButton = ViewFactory.createJButton("Add");
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				addMedia();
			}
		});
		
		JButton deleteButton = ViewFactory.createJButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				deleteMedia();
			}
		});
		
		outPanel.add(addButton,BorderLayout.WEST);
		outPanel.add(deleteButton,BorderLayout.EAST);
		
		return outPanel;
	}
	
	private void addMedia(){
		String path = chooseFileAddToList("Choose the media file");
		//add media here
			
	}
	
	private void deleteMedia(){		
		if(mySeletedIndex > -1){
			//delete media here
			listModel.remove(mySeletedIndex);			
		}		
	}
	
	public String chooseFileAddToList(String displayText){
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
				//load into engine
				ImageIcon icon = new ImageIcon(ImageIO.read(chooser.getSelectedFile()));
				Image img = icon.getImage();
				Image newimg = img.getScaledInstance( 50, 50, Image.SCALE_SMOOTH ) ;
				icon = new ImageIcon( newimg );
				listModel.addElement(icon);
			}
			return chooser.getSelectedFile().getName();
		}catch(Exception e){
			return null;
		}
	}


}
