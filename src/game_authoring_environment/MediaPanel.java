package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import controller.GAEController;

public class MediaPanel extends Panel {

	private SubPanel mySubPanel;
	private JTable myMediaTable;
	private int mySeletedRow = -1;
	private DefaultTableModel mediaTableModel;
	private GAEController gController;

	public MediaPanel(GAEController gController){
		super(PanelType.MEDIA);
		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		makeLists();
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(mySubPanel),BorderLayout.NORTH);
		this.add(new JScrollPane(myMediaTable),BorderLayout.CENTER);
	}


	private void makeLists() {
		myMediaTable = new JTable();
		mediaTableModel = new DefaultTableModel(new Object[]{"Media", "Name"}, 0){
			@Override
			public Class<?> getColumnClass(int col) {
				if (col == 1) {
					return super.getColumnClass(col);
				} else {
					return ImageIcon.class;
				}
			}
			@Override
			public boolean isCellEditable(int row, int column){  
				return false;  
			}
		};
		myMediaTable.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
		myMediaTable.setModel(mediaTableModel);
		ListSelectionModel cellSelectionModel = myMediaTable.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mySeletedRow = myMediaTable.getSelectedRow();	        
			}
		});
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
		if(mySeletedRow > -1){
			//delete media here
			mediaTableModel.removeRow(mySeletedRow);			
		}		
	}

	public String chooseFileAddToList(String displayText){
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
				//load into engine
				ImageIcon icon = new ImageIcon(ImageIO.read(chooser.getSelectedFile()));
				Image img = icon.getImage();
				Image newimg = img.getScaledInstance( 50, 50, Image.SCALE_SMOOTH ) ;
				icon = new ImageIcon( newimg );
				Object toAdd[] = {icon , chooser.getSelectedFile().getName()};
				mediaTableModel.addRow(toAdd);
				myMediaTable.setRowHeight(icon.getIconHeight() +2);
				myMediaTable.getColumnModel().getColumn(0).setMaxWidth(icon.getIconWidth() +2);
			}
			return chooser.getSelectedFile().getName();
		}catch(Exception e){
			return null;
		}
	}


}